from selenium import webdriver
from bs4 import BeautifulSoup
from selenium.webdriver.common.by import By
import time
from openpyxl import load_workbook

driver = webdriver.Chrome('./chromedriver.exe')
#크롬 드라이버에 url 주소 넣고 실행
workbook=load_workbook("C:/Users/82109/Desktop/데이터 수집/비교과공지사항.xlsx")

# 페이지가 완전히 로딩되도록 3초동안 기다림
driver.get('https://onevent.kookmin.ac.kr/ko/program')

for i in range(3,7):  ## 9페이지
    time.sleep(3)
    html = driver.page_source # 페이지의 elements모두 가져오기
    soup = BeautifulSoup(html, 'html.parser')
    
    elem=soup.select('body > div:nth-child(2) > main > div.container > div > ul.columns-4 > li')
    #body > div:nth-child(2) > main > div > div > ul.columns-4 > li:nth-child(1)
    #body > div:nth-child(2) > main > div.container > div > ul.columns-4 > li:nth-child(1)
    
    #body > div:nth-child(2) > main > div.container > div > ul.columns-4 > li:nth-child(1) > div > a
    #body > div:nth-child(2) > main > div.container > div > ul.columns-4 > li:nth-child(1) >div > a > div.content
    #body > div:nth-child(2) > main > div.container > div > ul.columns-4 > li:nth-child(1) > div > a > div.content > small:nth-child(4)
    datas=[] 
    for li in elem:
        title=li.select_one('div > a > div.content > b').get_text().replace('\n', '').replace('\t', '')
        url=li.select_one('div > a').get('href')
        date=li.select_one('div > a > div.content > small:nth-child(4)').get_text().replace('신청:','')
        datas.append([title,url,date])
    
    
    worksheet = workbook['Sheet1']
    for data in datas:
        worksheet.append(data)

    workbook.save("C:/Users/82109/Desktop/데이터 수집/비교과공지사항.xlsx")
   
   #body > div:nth-child(2) > main > div > div:nth-child(1) > div.pagination > div > div > ul > li:nth-child(3) > a
    nextpage = driver.find_element(By.CSS_SELECTOR,f"body > div:nth-child(2) > main > div > div:nth-child(1) > div.pagination > div > div > ul > li:nth-child({i})>a")
    nextpage.click()