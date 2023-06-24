from selenium import webdriver
from bs4 import BeautifulSoup
from selenium.webdriver.common.by import By
import time
from openpyxl import load_workbook

driver = webdriver.Chrome('./chromedriver.exe')
#크롬 드라이버에 url 주소 넣고 실행
workbook=load_workbook("C:/Users/82109/Desktop/데이터 수집/학교공지사항.xlsx")

# 페이지가 완전히 로딩되도록 3초동안 기다림
driver.get('https://www.sejongstudent1940.com/20')

for i in range(3,11):
    time.sleep(3)
    html = driver.page_source # 페이지의 elements모두 가져오기
    soup = BeautifulSoup(html, 'html.parser')
    cnt=0;
    elem=soup.select('#post_card_b20230228fc6fdd681795e > ul')
    ##post_card_b20230228fc6fdd681795e > ul:nth-child(2) > span > div.clearfix.table-cell.list-group.list_text_title > li.tit > a
    datas=[]
    for tr in elem:
        if cnt==0:
            cnt=cnt+1
            continue
        
        title=tr.select_one('span > div.clearfix.table-cell.list-group.list_text_title > li.tit > a > span:nth-child(3)').get_text().replace('\n', '').replace('\t', '')
        url="https://www.sejongstudent1940.com"+tr.select_one('span > div.clearfix.table-cell.list-group.list_text_title > li.tit > a').get('href')#.replace('/viewcount.do?rtnUrl=','board.sejong.ac.kr').replace('^','&')
        date=tr.select_one('span > div.clearfix.table-cell.list-group.list_text_title > li.time').get_text()
        datas.append([title,url,date])
    
    ##post_card_b20230228fc6fdd681795e > ul:nth-child(2) > span > div.clearfix.table-cell.list-group.list_text_title > li.time
    
    worksheet = workbook['Sheet1']
    for data in datas:
        worksheet.append(data)

    workbook.save("C:/Users/82109/Desktop/데이터 수집/학교공지사항.xlsx")
   
    nextpage = driver.find_element(By.CSS_SELECTOR,f"#w20230228d26dd2a19a4e4 > div > div.widget.board._list_wrap.m-margin-on.no-follow-grid > nav > ul > li:nth-child({i}) > a")
    nextpage.click()
    