from selenium import webdriver  # 自动打开网页获取以便获取动态的cookie
from bs4 import BeautifulSoup  # 解析网页为树状结构的模块
import re  # 使用正则规则查找数据的位置的模块
import xlwt  # 将数据写入csv文件的模块
import time  # 防止爬取太过频繁而触动防爬机制的休眠模块
import random  # 产生随机数模块


# 获取一个公司的旗下的岗位
# 定义一个主函数
def main():
    # 基本的url信息，后面加数字代表页数
    # 公司的岗位列表url
    baseurl = "https://www.zhipin.com/gongsir/8d038826246b5fb31nV83d4~_100000.html?"
    # 获取自己想要的数据以一个列表的形式展现
    datalist = getData(baseurl)
    # 保存路径
    savepath = "C:/Users/杨开/Desktop/泛微网络岗位链接.xls"
    # 将datalist写入为xls文件
    saveData(datalist, savepath)


def askURL(url):
    bro = webdriver.Chrome(executable_path="C:/Users/杨开/AppData/Local/Google/Chrome/Application/chromedriver.exe")
    # 让浏览器发起一个指定的请求
    bro.get(url)
    # 获取浏览器源代码数据
    html = bro.page_source
    time.sleep(random.randint(1, 10))  # 增加访问时间间隔与随机性防止反爬
    bro.quit()
    return html

# 标签的正则表达式
findjop_href = re.compile(r'data-jid="(.*?)"')  # 获取工作id
findjop_name = re.compile(r'title="(.*?)".*?</h1>')  # 工作名称规则
findjop_income = re.compile(r'<span class="salary">(.*?)</span>')  # 工作收入规则
findjop_city = re.compile(r'<a class="text-city".*>(.*?)</a>')  # 工作的城市
findjop_workyear = re.compile(r'</em>(.*?)<em class="dolt">')  # 工作经验
findjop_education = re.compile(r'</a><em class="dolt"></em>.*<em class="dolt"></em>(.*?)</p>')  # 工作学历
findjop_skills = re.compile(r'<div class="text">[\s\D]*(.*?)[\s\D]*</div>')  # 工作所需技能
findjop_area = re.compile(r'<div class="location-address">(.*?)</div>')  # 工作的地域


def getData(baseurl):
    # 设定空列表储存数据分析师，数据挖掘师，数据架构师，人工智能招聘数据
    datalist = []
    # 每次循环得到一次招聘信息以列表的形式保存在datalist中
    for k in range(1, 10):
        url = baseurl  + "page=" + str(k) + "&ka=page-" + str(k)
        # break
        html = askURL(url)
        soup = BeautifulSoup(html, "html.parser")
        # 找到需要分析的源代码大致范围
        item = soup.find_all("div",class_="job-list")
        # 转化为字符串
        item = str(item)
        # 使用正则表达式获取job_id
        jop_href = re.findall(findjop_href, item)
        # 将工作id拼接成html页面
        for job_id in jop_href:
            # 将获取到的岗位id拼接成网站的url继续爬取详细信息
            data = GetJobDetail("https://www.zhipin.com/job_detail/" + str(job_id).strip('"') + ".html")
            # 打印工作详情
            print(data)
            # 每次循环得到一次招聘信息以列表的形式保存在datalist中
            datalist.append(data)
    #返回工作岗位列表
    return datalist



def GetJobDetail(url):
    # 设定空列表储存岗位数据
    # 每次循环得到一次招聘信息以列表的形式保存在datalist中
    html = askURL(url)
    # 对每次循环的网页进行页面解析，通过正则匹配获取自己想要的数据
    # ptint(html)
    soup = BeautifulSoup(html, "html.parser")
    item = soup.find_all(id='main')  # 找到需要分析的源代码大致范围

    data = []  # 保存一个招聘的信息
    item = str(item)

    # 使用正则表达式获取想要的数据标签信息
    # 例如工作名称,工作收入,工作城市,工作经验,工作学历,工作概要，工作地点
    jop_name = re.findall(findjop_name, item)
    data.append(jop_name)

    jop_income = re.findall(findjop_income, item)
    data.append(jop_income)

    jop_city = re.findall(findjop_city, item)
    data.append(jop_city)

    jop_workyear = re.findall(findjop_workyear,item)
    data.append(jop_workyear)

    jop_education = re.findall(findjop_education, item)
    data.append(jop_education)

    jop_skills = re.findall(findjop_skills, item)
    data.append(jop_skills)

    jop_area = re.findall(findjop_area, item)
    data.append(jop_area)

    return data


# 保存数据到xls文件
def saveData(datalist, savepath):
    book = xlwt.Workbook(encoding="utf-8", style_compression=0)  # 创建workbook对象
    sheet = book.add_sheet("泛微网络岗位详细数据", cell_overwrite_ok=True)  # 创建工作表cell_overwrite_ok=True,每次循环覆盖前一次表
    col = ("岗位名称","薪资","工作城市","经验","学历","职位描述","工作地址")
    for i in range(7):
        # 行，列，写入数据
        sheet.write(0, i, col[i])
    for i in range(len(datalist)):
        data = datalist[i]
        for j in range(len(data)):
            # 行，列，写入数据
            sheet.write(i + 1, j, data[j])
    book.save(savepath)


# 当运行程序时,运行主函数
if __name__ in "__main__":
    main()