#!/usr/bin/python
# -*- coding: UTF-8 -*-
from math import sqrt
import operator

#1.构建用户-->物品的倒排
def loadData(files):
    data ={}
    for line in files:
        user,score,item=line.split(",")
        data.setdefault(user,{})
        print(user)
        print(score)
        print(item)
        data[user][item]=score
    print("----1.用户：物品的倒排----")
    print(data)
    return data

#2.计算
# 2.1 构造物品-->物品的共现矩阵
# 2.2 计算物品与物品的相似矩阵
def similarity(data):
    # 2.1 构造物品：物品的共现矩阵
    N={}#喜欢物品i的总人数
    C={}#喜欢物品i也喜欢物品j的人数
    for user,item in data.items():
        for i,score in item.items():
            N.setdefault(i,0)
            N[i]+=1
            C.setdefault(i,{})
            for j,scores in item.items():
                if j not in i:
                    C[i].setdefault(j,0)
                    C[i][j]+=1

    print("---2.构造的共现矩阵---")
    print ('N:',N)
    print ('C',C)

    #2.2 计算物品与物品的相似矩阵
    W={}
    for i,item in C.items():
        W.setdefault(i,{})
        for j,item2 in item.items():
            W[i].setdefault(j,0)
            W[i][j]=C[i][j]/sqrt(N[i]*N[j])
    print("---3.计算的相似矩阵---")
    print(W)
    return W

#3.根据用户的历史记录，给用户推荐物品
def recommandList(data,W,user,k=3,N=10):
    rank={}
    for i,score in data[user].items():#获得用户user历史记录，如A用户的历史记录为{'a': '1', 'b': '1', 'd': '1'}
        for j,w in sorted(W[i].items(),key=operator.itemgetter(1),reverse=True)[0:k]:#获得与物品i相似的k个物品
            if j not in data[user].keys():#该相似的物品不在用户user的记录里
                rank.setdefault(j,0)
                rank[j]+=float(score) * w

    print("---3.推荐----")
    print(sorted(rank.items(),key=operator.itemgetter(1),reverse=True)[0:N])
    return sorted(rank.items(),key=operator.itemgetter(1),reverse=True)[0:N]


if __name__=='__main__':
    print("---1.构造数据---")
    data = {'Lisa Rose': {'Lady in the Water': 2.5, 'Snakes on a Plane': 3.5,
                          'Just My Luck': 3.0, 'Superman Returns': 3.5, 'You, Me and Dupree': 2.5,
                          'The Night Listener': 3.0},

            'Gene Seymour': {'Lady in the Water': 3.0, 'Snakes on a Plane': 3.5,
                             'Just My Luck': 1.5, 'Superman Returns': 5.0, 'The Night Listener': 3.0,
                             'You, Me and Dupree': 3.5},

            'Michael Phillips': {'Lady in the Water': 2.5, 'Snakes on a Plane': 3.0,
                                 'Superman Returns': 3.5, 'The Night Listener': 4.0},

            'Claudia Puig': {'Snakes on a Plane': 3.5, 'Just My Luck': 3.0,
                             'The Night Listener': 4.5, 'Superman Returns': 4.0,
                             'You, Me and Dupree': 2.5},

            'Mick LaSalle': {'Lady in the Water': 3.0, 'Snakes on a Plane': 4.0,
                             'Just My Luck': 2.0, 'Superman Returns': 3.0, 'The Night Listener': 3.0,
                             'You, Me and Dupree': 2.0},

            'Jack Matthews': {'Lady in the Water': 3.0, 'Snakes on a Plane': 4.0,
                              'The Night Listener': 3.0, 'Superman Returns': 5.0, 'You, Me and Dupree': 3.5},

            'Toby': {'Snakes on a Plane': 4.5, 'You, Me and Dupree': 1.0, 'Superman Returns': 4.0}
            }
    W=similarity(data)#计算物品相似矩阵
    recommandList(data,W,'Toby',10,10)#推荐