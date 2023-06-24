## 건대의 경우 페이지 번호 누르는 것만 계속 수정해서 크롤링 하면 됨

from selenium import webdriver
from bs4 import BeautifulSoup
from selenium.webdriver.common.by import By
import time
from openpyxl import load_workbook

driver = webdriver.Chrome('./chromedriver.exe')
#크롬 드라이버에 url 주소 넣고 실행
workbook=load_workbook("C:/Users/82109/Desktop/데이터 수집/학교공지사항.xlsx")

# 페이지가 완전히 로딩되도록 3초동안 기다림
driver.get('https://friend.konkuk.ac.kr/do/MessageBoard/ArticleList.do?forum=notice&p_rel=9&sort=6&p=0&p_rel=-2%20class=first-child')
time.sleep(3)
for i in range(2,7):
    
    html = driver.page_source # 페이지의 elements모두 가져오기
    soup = BeautifulSoup(html, 'html.parser')
    ul = soup.select_one('#content > form:nth-child(1) > table > tbody')
    elem=ul.select('tr')
    
    datas=[]
    for tr in elem:
        title=tr.select_one('td.subject').get_text().replace('\n', '').replace('\t', '')
        url="https://"+tr.select_one('td.subject > a').get('href').replace('ArticleRead.do','/friend.konkuk.ac.kr/do/MessageBoard/ArticleRead.do')
        date=tr.select_one('td:nth-child(4)').get_text()
        datas.append([title,url,date])
    
    worksheet = workbook['Sheet1']
    for data in datas:
        worksheet.append(data)

    workbook.save("C:/Users/82109/Desktop/데이터 수집/학교공지사항.xlsx")
   ##content > form:nth-child(1) > div.paginate > a:nth-child(5)
   ##content > form:nth-child(1) > div.paginate > a:nth-child(2)
    nextpage = driver.find_element(By.CSS_SELECTOR,f"#content > form:nth-child(1) > div.paginate > a:nth-child({i})")
    nextpage.click()
    time.sleep(3)
    
#content > form:nth-child(1) > table > tbody