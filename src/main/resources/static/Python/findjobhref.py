from selenium import webdriver  # 自动打开网页获取以便获取动态的cookie
from bs4 import BeautifulSoup  # 解析网页为树状结构的模块
import re  # 使用正则规则查找数据的位置的模块
import xlwt  # 将数据写入csv文件的模块
import time  # 防止爬取太过频繁而触动防爬机制的休眠模块
import random  # 产生随机数模块


# 获取一个公司的岗位详情链接
# 定义一个主函数
def main():
    # 基本的url信息，后面加数字代表页数
    baseurl = "https://www.zhipin.com/gongsir/8d038826246b5fb31nV83d4~_100000.html?"
    # 获取自己想要的数据以一个列表的形式展现
    datalist = getData(baseurl)
    # 保存路径
    savepath = "C:/Users/杨开/Desktop/泛微网络岗位链接.xls"
    # 将datalist写入为xls文件
    saveData(datalist, savepath)


def askURL(url):
    # chromeOptions = webdriver.ChromeOptions()
    # # 设置代理
    # chromeOptions.add_argument(getip())
    # 一定要注意，=两边不能有空格，不能是这样--proxy-server = http://202.20.16.82:10152
    # 实例化一个浏览器对象(传入驱动程序的位置）
    bro = webdriver.Chrome(executable_path="C:/Users/杨开/AppData/Local/Google/Chrome/Application/chromedriver.exe")
    # bro = webdriver.Chrome(executable_path="C:/Users/HUAWEI/Desktop/chromedriver.exe",chrome_options=chromeOptions)
    # 让浏览器发起一个指定的请求
    bro.get(url)
    # 获取浏览器源代码数据
    html = bro.page_source
    # print(type(html))
    time.sleep(random.randint(1, 10))  # 增加访问时间间隔与随机性防止反爬
    bro.quit()
    return html


# 标签的正则表达式
findjop_href = re.compile(r'data-jid="(.*?)"')  # 获取工作id

# 由获取一个页面信息发展到多个页面,并解析网页获取数据
# baseurl="https://www.zhipin.com/"
def getData(baseurl):
    # 设定空列表储存数据分析师，数据挖掘师，数据架构师，人工智能招聘数据
    datalist = []
    # 每次循环得到一次招聘信息以列表的形式保存在datalist中
    for k in range(1, 8):
        url = baseurl  + "page=" + str(k) + "&ka=page-" + str(k)
        # print(url)
        # break
        html = askURL(url)
        # 对每次循环的网页进行页面解析，通过正则匹配获取自己想要的数据
        # ptint(html)
        # break
        soup = BeautifulSoup(html, "html.parser")

        item = soup.find_all("div",class_="job-list")  # 找到需要分析的源代码大致范围
        # 转化为字符串
        item = str(item)
        # 使用正则表达式获取job_id
        jop_href = re.findall(findjop_href, item)
        # 将工作id拼接成html页面
        for job_id in jop_href:
            data = []  # 保存一个招聘的信息
            data.append("https://www.zhipin.com/job_detail/" + str(job_id).strip('"') + ".html")
            datalist.append(data)  # 每次循环得到一次招聘信息以列表的形式保存在datalist中
    return datalist


# getData(baseurl)

# 保存数据到xls文件
def saveData(datalist, savepath):
    book = xlwt.Workbook(encoding="utf-8", style_compression=0)  # 创建workbook对象
    sheet = book.add_sheet("泛微网络岗位链接", cell_overwrite_ok=True)  # 创建工作表cell_overwrite_ok=True,每次循环覆盖前一次表
    col = ("岗位链接")
    for i in range(1):
        # 行，列，写入数据
        sheet.write(0, 0, col)
    for i in range(len(datalist)):
        data = datalist[i]
        for j in range(len(data)):
            # 行，列，写入数据
            sheet.write(i + 1, j, data[j])
    book.save(savepath)


# 当运行程序时,运行主函数
if __name__ in "__main__":
    main()