from selenium import webdriver
from bs4 import BeautifulSoup
from selenium.webdriver.common.by import By
import time
from openpyxl import load_workbook

driver = webdriver.Chrome('./chromedriver.exe')
#크롬 드라이버에 url 주소 넣고 실행
workbook=load_workbook("C:/Users/82109/Desktop/데이터 수집/학교공지사항.xlsx")

# 페이지가 완전히 로딩되도록 3초동안 기다림
driver.get('https://www.hanyang.ac.kr/web/www/notice_all?p_p_id=viewNotice_WAR_noticeportlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_viewNotice_WAR_noticeportlet_sCategoryId=0&_viewNotice_WAR_noticeportlet_sCurPage=1&_viewNotice_WAR_noticeportlet_sUserId=0&_viewNotice_WAR_noticeportlet_action=view')

for i in range(4,9):
    time.sleep(3)
    html = driver.page_source # 페이지의 elements모두 가져오기
    soup = BeautifulSoup(html, 'html.parser')
    
    elem=soup.select('#notice01 > div > table > tbody>tr')
    ##notice01 > div > table > tbody > tr:nth-child(1) > td > div > div > div.notice-date
    ##notice01 > div > table > tbody > tr:nth-child(1) > td > div > div > p > a
    datas=[]
    for tr in elem:
        title=tr.select_one('td > div > div > p').get_text().replace('\n', '').replace('\t', '')
        url=tr.select_one('td > div > div > p > a').get('href').replace('javascript:_viewNotice_WAR_noticeportlet_view_message(','www.hanyang.ac.kr/web/www/notice_all?p_p_id=viewNotice_WAR_noticeportlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_viewNotice_WAR_noticeportlet_sCategoryId=0&_viewNotice_WAR_noticeportlet_sCurPage=1&_viewNotice_WAR_noticeportlet_sUserId=0&_viewNotice_WAR_noticeportlet_action=view_message&_viewNotice_WAR_noticeportlet_messageId=').replace(');','')
        date=tr.select_one('td > div > div > div.notice-date').get_text()
        datas.append([title,url,date])
    
    worksheet = workbook['결과']
    for data in datas:
        worksheet.append(data)

    workbook.save("C:/Users/82109/Desktop/데이터 수집/학교공지사항.xlsx")
   
    nextpage = driver.find_element(By.CSS_SELECTOR,f"#notice01 > div > div.pagination.pagination-hy.text-center > ul > li:nth-child({i}) > a")
    nextpage.click()
    
##notice01 > div > div.pagination.pagination-hy.text-center > ul > li:nth-child(4) > a
##notice01 > div > div.pagination.pagination-hy.text-center > ul > li:nth-child(5) > a





##notice01 > div > table > tbody