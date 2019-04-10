# **礼遇圈接口文档**

> * 版本号 v0.0.1
> * 最后更新时间 2018/2/7
> * 更新人 yichen

## 更新记录




### v0.0.1

* 更新人：yichen
* 2018/6/5
* 更新内容

> 简历初稿


### v0.0.2

* 更新人：linqin
* 2018/12/01
* 更新内容

>  1. 增加修改银行卡接口   （3.15接口）
>  2. 修改银行卡列表接口，增加默认银行卡字段 （ 3.4接口）
>  3. 增加获取sku库存接口   （6.17接口）
>  4. 增加默认sku     （7.5接口）

### v0.0.3

* 更新人：yichen
* 2018/12/6
* 更新内容

> 1. 修改索要礼物列表接口，增加type (接口14.7)
> 2. 增加系统消息好友头像和id  （接口16.2）
> 3. 增加行政区列表接口  （4.2）
> 4. 修改搜索好友接口 （10.11）

### v0.0.4

* 更新人：yichen
* 2018/12/6
* 更新内容

> 1. 增加根据skuId获取商品详情 (接口6.18)
> 2. 增加删除索要记录接口 （14.10）
### v0.0.5

* 更新人：yichen
* 2019/1/4
* 更新内容

> 1. 增加赠送记录/收礼记录 (接口15.7)
> 2. 增加撤回礼物赠送接口（15.8）
> 3. 增加删除赠送记录接口（15.9）
> 4. 增加删除收礼记录接口（15.10）
> 5. 修改备忘录相关接口 （11）
> 6. 修改秀秀相关接口 （15）
> 7. 修改文章列表 （6.1）
> 8. 修改文章详情 （6.2）
> 9. 增加文章商品列表接口 （6.19）
> 10. 增加商城标签列表接口 （6.20）
> 11. 增加标签商品列表接口（6.21）
> 12. 增加按天获取文章列表 (17.5)
> 13. 增加用户未查看的评论和点赞(12.10)
> 14.  增加首页节日列表（17.6）

### v0.0.5

* 更新人：yichen
* 2019/1/22
* 更新内容

> 1. 修改索要记录接口（增加品牌字段） (接口14.7)
> 2. 修改评论列表接口，增加用户id,昵称字段（6.10）
> 3. 增加新的好友消息未查看数量（通讯录红点）接口（10.12）
> 4. 修改商品详情接口（加状态）（6.9）
> 5. 修改收藏列表接口（加状态）（6.12）
> 6. 修改购物车列表接口（加状态）（7.1）
> 7. 增加商品提货前判断商品是否下架（背包物品提货前调用）（关于下架）（13.6）
> 8. 增加修改用户电话号码接口（2.13）
> 9. 修改添加备忘录事件接口（11.9）
> 10. 增加看过的消息改为为已读接口（关于消息已读/未读）（16.3）



### v0.0.6

* 更新人：yichen
* 2019/2/13
* 更新内容

> 1. 修改分组列表（增加参数） (接口10.8)
> 2. 增加商品文章搜索 （6.22 ）
> 3. 背包列表（返回参数增加两个字段）（14.1）
> 4. 修改用户手机号（增加参数）(2.13)
> 5. 增加福利接口（21.1，  21.2）

### v0.0.7

* 更新人：yichen
* 2019/2/26
* 更新内容

> 1. 增加首页黄历宜忌接口（17.7）
> 2. 增加个推绑定别名接口（2.14）


### v0.0.8

* 更新人：yichen
* 2019/3/11
* 更新内容

> 1. 修改首页黄历宜忌接口（17.7）
> 2. 备忘录2.0接口（22）
> 3. 新加app礼物赠送接口（15.11）
> 4. 小程序折现/提货（13.7/13.8）
> 5. app礼物赠送接口（15.11）增加参数
> 6. 礼物赠送消息返回字段isReplay值修改
> 7. 小程序折现记录（13.9）


### v0.0.9

* 更新人：yichen
* 2019/3/20
* 更新内容

> 1. 增加商品文章事件搜索接口（6.23）
> 2. 增加给好友留言接口(10.13)



### v0.1.0

* 更新人：yichen
* 2019/4/3
* 更新内容

> 1. 修改绑定手机号接口（2.2）
> 2. 礼花相关接口（23）
> 3. 增加订单支付方式（接口7.6,7.7）
> 4. 增加虚拟订单支付方式（接口6.16）
> 5. 订单列表修改返回值（增加支付方式，接口7.9）
> 6. 修改添加银行卡（绑定支付宝）接口（3.5）
> 7. 修改用户银行卡列表返回值（bankId= 17 是支付宝）接口（3.4）
> 8. 修改支付宝（3.15）删除支付宝（3.6）（与删除修改银行卡公用接口）
> 9. 修改商品分类返回值（接口6.7）
> 10. 增加设置隐私接口（2.15） 



## 目录
<span id="m"> </span>

[TOC]


## 1 接口说明
### 1.1 接口访问地址
https://liyuquan.cn/app/
### 1.2 接口签名
apikey
```java
Android端：m0BKP^I^2NGH5y$kP3Ks38kN26VaGH%Z
IOS端：%sX4H91PzuB7V^T4uefDnsiwzHDxOgrX
小程序：mRrBzm9OiRCq&^frt7c#V$b3pQQLemRv
```
### 1.3 接口访问示例
参数：userId:1 ,name:abc;
则Android端sign的值为：
MD5(name=abc&time=1507544919000&userId=1&key= YXgqgMk@RiPFxXi64hrRoA1JPd32wvz!4)
最后访问：
https://io.shanren.group/
app/demoget/v1?sign= 24C9167E2E8E018749D0B984917AA71F&userId=1&name=abc&time=1507544919000

### 1.4 接口响应 [↑](#m)

```javascript
{
    errCode: 0,
    msg: "错误信息",
    data: { },
    time:1589437847788
}
```

| 字段名称        | 字段类型       | 是否必传 | 描述  |
| ------------- |:--------:|:---------:|:--------:|
| errCode | Int | 是 | 错误码 |
| msg | String | 否 | 错误信息 |
| data | Object | 否 | 成功返回数据集 |
| time | Long | 是 | 接口返回时服务器的时间戳 |

### 1.5 接口错误码表

| 错误码        | 错误描述       |
| ------------- |:-------------:|
| 0             | 请求操作成功 |
| 1             | 请求操作失败 |
| 4             | 缺少参数 |
| 5 | 签名错误 |
| 1002 | 需要登录 |
| 1003 | 权限拒绝 |
| 1004 | 第三方登录时需要绑定手机号不绑定的话不能登录 |
| 1005 | 用户不存在 |
| 1006 | 短信验证码错误 |
| 1007 | 手机号已注册 |
| 1008 | appId 不存在 |
| 1009 | 无效的refresh_token |
| 1012 | 用户已加入其它未开始活动的战队 |

http 常用错误码

| 错误码        | 错误描述       |
| ------------- |:-------------:|
| 400 | 错误请求  服务器不理解请求的语法 |
| 401 | 未授权 需填写正确的access_token |
| 404 | （未找到） 服务器找不到请求的网页 |
| 405 | （方法禁用） 禁用请求中指定的方法 |
| 500 | 服务器内部错误，具体查看msg输出的内容 |



## 2.用户信息

### 2.1 登录

- 请求地址：noauth/user/wxLogin
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
|   code   |  string  |    是    |   无   | 微信授权 |

- 请求结果示例

```js
// 登录未绑定手机号返回
{
    errCode:1004, // 未绑定手机号
    data: "dagagsdfwerewrewrew" // key 用于下一步绑定手机号
}
// 登录成功返回
{
    errCode: 0,
    data: {
        access_token: "访问令牌",
        refresh_token: "用于刷新访问令牌"
    }
}
```

### 2.2 第三方账号绑定手机号

- 请求地址：noauth/user/bindPhone
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

| 参数名称 | 参数类型 | 是否必传 | 默认值 |    参数说明     |
| :------: | :------: | :------: | :----: | :-------------: |
|  phone   |  String  |    是    |   无   |      电话       |
|   key    |  String  |    是    |   无   | 缓存openId的key,2.1中返回的data内容 |
|   key    |  String  |    是    |   无   | 缓存openId的key,2.1中返回的data内容 |
|   code    |  String  |    是    |   无   | 短信验证码 |
| userId|int|否|无|邀请好友凭证（邀请者用户Id）|

- 请求结果示例

```js
// 登录成功返回
{
    errCode: 0,
    data: {
        access_token: "访问令牌",
        refresh_token: "用于刷新访问令牌"
    }
}
```


### 2.3 获取用户详细信息

- 请求地址：auth/user/profile
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  String  |    是    |   无   | 访问令牌 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1528188439203, 
    "data": {
        "id": 1, 
        "account": "15752400657", 
        "password": "", 
        "phone": "15752400657", 
        "avatar": "avatar.jpg",  // 头像
        "nickname": "三生石",  // 昵称
        "age": 21, // 年龄
        "gender": 2, // 性别 1 男 2 女
        "signature": "没有", // 签名
        "district": "昆明", // 地区
        "status": 1, 
        "sentPwd": "123456", 
        "wxid": "123", 
        "qrcode": "32143254255435345", // 用户二维码
        "created": 1528187653000, 
        "updated": 1528188429000
    }
}
```

### 2.4 修改用户信息

- 请求地址：auth/user/modify_profile
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 |         是否必传          |
| :----------: | :------: | :------: | :----: | :-----------------------: |
| access_token |  String  |    是    |   无   |         访问令牌          |
|    avatar    |  String  |    否    |   无   |           头像            |
|   nickname   |  String  |    否    |   无   |           昵称            |
|     age      |   int    |    否    |   无   |           年龄            |
|    gender    |   int    |    否    |   0    | 性别（默认0），1-男，2-女 |
|  signature   |  String  |    否    |   无   |         个性签名          |
|   district   |  String  |    否    |   无   |           地区            |

请求结果示例：

```json
{
"errCode": 0,
"result": 0,
"msg": "修改成功",
"time": 1528255310631
}
```



### 2.5 意见反馈

- 请求地址： auth/v1/suggestion/feedback
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 |                       参数说明                       |
| :----------: | :------: | :------: | :----: | :--------------------------------------------------: |
| access_token |  String  |    是    |   无   |                       访问令牌                       |
|     type     |   int    |    是    |   无   | 意见类型，1-提个建议，2-程序出错啦，3-不好用，4-其他 |
|  contactWay  |  String  |    是    |   无   |                       联系方式                       |
|   feedback   |  String  |    是    |   无   |                     意见反馈文字                     |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "反馈成功", 
    "time": 1528378339653
}
```
### 2.6 发送短信验证
- 请求地址：ask/code
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| phone |  string  |    是    |   无   | 电话号码 |
| type |  int  |    是    |   无   | 验证码类型 1 绑定手机号 2 重置赠送密码 3 修改用户号码，4-小程序提货验证手机 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "发送成功!", 
    "time": 1529462478025
}
```

### 2.7 获取可添加的好友印象标签

- 请求地址：auth/user/tag/list
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| 无参数 |

请求结果示例：

```json
{
    errCode: 0, 
    result: 0, 
    time: 1529591100815, 
    data: [
        {
            id: 36, 
            tag: "不合群", 
            type: 2, // 标签颜色 1 蓝色 2 粉色
            created: 1529588441000, 
            updated: 1529588441000
        }, 
        {
            id: 35, 
            tag: "表里不一", 
            type: 2, 
            created: 1529588441000, 
            updated: 1529588441000
        }
    ]
}
```

### 2.8 添加好友印象标签

- 请求地址：auth/user/tag/add
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token | string | 是 | 无 | 访问令牌 |
| friendUserId | int | 是 | 无 | 好友用户id |
| tagIds | string | 是 | 无 | 标签id集合（多个,隔开）-2.6中的id |



### 2.9 查看礼物偏好

- 请求地址：auth/user/gift/preference/list
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token | string | 是 | 无 | 访问令牌 |
| targetUserId | int | 否 | 无 | 要查看的用户id 不传查看自己的 |

* 请求结果示例
* 如果是查看自己的会返回所有可选择的礼物偏好
* 查看别人的只返回别人已选择的礼物偏好

```js
{
    errCode: 0, 
    result: 0, 
    time: 1529633323835, 
    data: [
        {
            id: 12, 
            text: "数码", 
            type: 2, // 只在看自己的礼物偏好时有效 1 我已选择 2 我还没选择
            created: 1529588757000, 
            updated: 1529588757000
        }, 
        {
            id: 13, 
            text: "古玩", 
            type: 1, 
            created: 1529588757000, 
            updated: 1529588757000
        }, 
        {
            id: 14, 
            text: "玉石", 
            type: 1, 
            created: 1529588757000, 
            updated: 1529588757000
        }
    ]
}
```


### 2.10 修改礼物偏好

- 请求地址：auth/user/gift/preference/modify
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token | string | 是 | 无 | 访问令牌 |
| ids | string | 否 | 无 | 选中的礼物偏好id集合，多个用,隔开 |


### 2.11 获取自己或别人的信息(包含印象表情和秀秀)

- 请求地址：auth/user/info
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token | string | 是 | 无 | 访问令牌 |
| targetUserId | int | 是 | 无 | 查看用户的id，不传默认查看自己的 |

```js
{
    errCode: 0, 
    result: 0, 
    time: 1530257357866, 
    data: {
        userId: 4, 
        phone: "183 *** 7954", 
        avatar: "https://io.shanren.group/image/avatar.jpg", 
        nickname: "大秦", 
        age: 2, 
        gender: 2, 
        signature: "多少", 
        district: "多少", 
        wxid: "", 
        isFriend: 1,  // 是否是好友关系 1 是 2 不是
        remark: "",   // 好友备注
        relationship: "",  // 我和好友的关系
        tags: [ // 好友印象标签
            {
                tagId: 22, 
                tag: "言行一致", 
                num: 1,  // 被表及的次数
                type: 1 // 1 蓝色 2 粉色 
            }, 
            {
                tagId: 23, 
                tag: "才华横溢", 
                num: 2, 
                type: 1
            }, 
            {
                tagId: 24, 
                tag: "出口成章", 
                num: 2, 
                type: 1
            }
        ], 
        recentMoments: [ // 最近发表过的三张照片
            {
                type: 1, 
                url: "https://io.shanren.group/image/pic.jpg"
            }, 
            {
                type: 1, 
                url: "https://io.shanren.group/image/pic.jpg"
            }, 
            {
                type: 1, 
                url: "https://io.shanren.group/image/pic.jpg"
            }
        ]
    }
}
```

### 2.12 扫描个人二维码

- 请求地址：auth/user/scanQrcode
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token | string | 是 | 无 | 访问令牌 |
| qrcode | string | 是 | 无 | 用户二维码内容 |

- 返回结果和2.11 一致




### 2.13 修改用户电话号码

- 请求地址：auth/user/modify_phone
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 |         是否必传          |
| :----------: | :------: | :------: | :----: | :-----------------------: |
| access_token |  String  |    是    |   无   |         访问令牌          |
|    phone    |  String  |    是    |   无   |           电话号码         |
|code|int|是|  无|短信验证码|


请求结果示例：

```json
{
"errCode": 0,
"result": 0,
"msg": "修改成功",
"time": 1528255310631
}
```


### 2.14 个推绑定别名

- 请求地址：auth/user/band
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 |         是否必传          |
| :----------: | :------: | :------: | :----: | :-----------------------: |
| access_token |  String  |    是    |   无   |         访问令牌          |
|    clientid    |  String  |    是    |   无   |           别名         |



请求结果示例：

```json
{
"errCode": 0,
"result": 0,
"time": 1528255310631
}
```


### 2.15 设置用户隐私

- 请求地址：auth/user/set_hide
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 |         是否必传          |
| :----------: | :------: | :------: | :----: | :-----------------------: |
| access_token |  String  |    是    |   无   |         访问令牌          |
|    isHide    |  byte  |    否    |   1   |           1 不开启，2开启（隐藏）         |


请求结果示例：

```json
{
"errCode": 0,
"result": 0,
"msg": "设置成功",
"time": 1528255310631
}
```


## 3. 我要--我的钱包

### 3.1 钱包详情

- 请求地址：auth/v1/wallet/detail
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1528380667306, 
    "data": {
        "userId": 1, 
        "balance": 40, 
        "totalAmount": 50, 
        "consumeAmount": 3, 
        "created": 1528380657000, 
        "updated": 1528380660000
    }
}
```

### 3.2 创建充值订单

- 请求地址：auth/v1/charge/order
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
|    amount    |   int    |    是    |   无   | 充值金额 |
|    payWay    |   int    |    是    |   无   | 支付方式，微信 24656，支付宝 78990 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1528422440302, 
    "data": {
        "params": "CcTxFSVe+MacO+YFCYnyTazCZN9Js0v6vlMMWQ9RKYLA3GPgc/50iKt3qenIcpXBUsx5g309Y6T/YmCYcCqilfCZEPf776MxPy9j8ni4M2/hmUm+TUfaJryw9mZlmRspdCKAhObeqSVfxPPCQ8yVgGvddRXAd1wUs/dvWCk2ezkkzO4OQ1BRCstRNiffXNfl96jVAPB4hEWIstnWvUSgnrkwtuHQTVuXudyGLcqWcjUrchq0fyFP242mg4sBMUcM8vVA2UxFr3AinDkZExna6RB8KTW3c7oi5X8ngDW8jfee8St3r7Ebv5q2nGH41fWGkBAUIppDUT3qPRHy3EwJFA==MMMmW6qy9uP6BdkPFpqUBlc1c70+slkyS5bcImcfIKBapgnPVn8P1ieba0W4GqaKfBwYGlBTsGVkr33bfkr6JqDbM7RwjLU/7Jc7GvF8CoA/jOOZ4LlV6g8szkoXm18VTbIctuNSN7u61mYlPwrh0k/e/9vejQLotn0y9jax/MLYWf7uehWipVbz7vNlCnll9ttNFYS0H8vzNZtFBHI0KIKpLabj9WuNJBzIgCMbBfZmmz7Br6AXiUVlUfQrTtAfKOe6Mi0iUkskPcTLFTlfaUtRCVrKFYjR+u1BZltoR2cHDhjbQYANMC0dJmBI7XORVjU6rX2B5laOoNR9uRUcVw==||344,344", 
        "orderNo": 120180610005, 
        "type": 24656
    }
}

```

### 3.3 提现--获取银行类型列表

- 请求地址：auth/v1/bankCard/bankList
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "time": 1528772444302,
    "data": [
        {
            "bankId": 1,
            "bankName": "中国银行",
            "logo": "http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132"
        },
        {
            "bankId": 2,
            "bankName": "工商银行",
            "logo": "http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132"
        },
        {
            "bankId": 3,
            "bankName": "农业银行",
            "logo": "http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132"
        }
    ]
}
```

| 参数名称 | 参数类型 | 是否必传 |         参数说明          |
| -------- | :------: | :------: | :-----------------------: |
| errCode  |   Int    |    是    | 错误码 0 标识成功获取数据 |
| data     |  Object  |    否    |       成功返回数据        |
| bankId   |   Int    |    是    |          银行id           |
| bankName |  String  |    是    |         银行名称          |
| logo     |  String  |    是    |         银行logo          |

### 3.4 提现--获取用户银行卡列表

- 请求地址：auth/v1/bankCard/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "time": 1528772676449,
    "data": [
         {
            "id": 23, 
            "bankId": 3, 
            "logo": "https://liyuquan.cn/static/bank/bankny.png", 
            "bankName": "农业银行", 
            "depositBank": "哈啊", 
            "cardHolder": "LOL", 
            "cardNo": "5443476348622", 
            "isDefault": 1
        }, 
        {
            "id": 22, 
            "bankId": 17, 
            "logo": "https://liyuquan.cn/static/bank/bankzg.png", 
            "bankName": "支付宝", 
            "depositBank": "咯旅游", 
            "cardHolder": "关机了", 
            "cardNo": "726456483463586871", 
            "isDefault": 2
        }
    ]
}
```

| 参数名称    | 参数类型 | 是否必传 |         参数说明          |
| ----------- | :------: | :------: | :-----------------------: |
| errCode     |   Int    |    是    | 错误码 0 标识成功获取数据 |
| data        |  Object  |    否    |       成功返回数据        |
| id          |   Int    |    是    |       用户银行卡id        |
| bankId      |   Int    |    是    |          银行id （bankId等于17时是支付宝）          |
| bankName    |  String  |    是    |         银行名称          |
| logo        |  String  |    是    |         银行logo          |
| depositBank |  String  |    是    |        开户行名称         |
| cardHolder  |  String  |    是    |        持卡人姓名         |
| cardNo      |  String  |    是    |         银行卡号          |
|isDefault|byte|是|是否为默认银行卡 1 默认，2 不是默认|

### 3.5 提现--添加用户银行卡/绑定支付宝

- 请求地址：auth/v1/bankCard/add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 |  参数说明  |
| :----------: | :------: | :------: | :----: | :--------: |
| access_token |  string  |    是    |   无   |  访问令牌  |
| depositBank  |  string  |    是    |   无   | 开户行名称/电话号码 |
|  cardHolder  |  string  |    是    |   无   | 持卡人姓名/收款人姓名 |
|    cardNo    |  string  |    是    |   无   |  银行卡号 /支付宝账号 |
|    bankId    |   Int    |    否    |   17   |   银行id(添加银行卡时是必传，绑定支付宝不传)  |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "msg": "添加成功",
    "time": 1528773060163
}
```

### 3.6 提现--删除用户银行卡/删除支付宝

- 请求地址：auth/v1/bankCard/del
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 |   参数说明   |
| :----------: | :------: | :------: | :----: | :----------: |
| access_token |  string  |    是    |   无   |   访问令牌   |
|      id      |   Int    |    是    |   无   | 用户银行卡id |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "msg": "删除成功",
    "time": 1528773291757
}
```

### 3.7 提现

- 请求地址：auth/v1/withdraw/add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 |   参数说明   |
| :----------: | :------: | :------: | :----: | :----------: |
| access_token |  string  |    是    |   无   |   访问令牌   |
|      id      |   Int    |    是    |   无   | 用户银行卡id |
|    amount    | decimal  |    是    |   无   |   提现金额   |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "msg": "提现申请成功!",
    "time": 1528774228877
}
```

### 3.8 提现记录

- 请求地址：auth/v1/withdraw/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
|   pageNum    |   Int    |    否    |   1    |   分页   |
|   pageSize   |   Int    |    否    |   14   | 分页大小 |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "time": 1528775410848,
    "data": [
        {
            "amount": 20,
            "status": 1,
            "created": 1528451950000,
            "describe": "提现成功失败原因"
        },
        {
            "amount": 20,
            "status": 1,
            "created": 1528774229000,
            "describe": ""
        }
    ]
}
```

| 参数名称 | 参数类型 | 是否必传 |                        参数说明                        |
| -------- | :------: | :------: | :----------------------------------------------------: |
| errCode  |   Int    |    是    |               错误码 0 标识成功获取数据                |
| data     |  Object  |    否    |                      成功返回数据                      |
| amount   | decimal  |    是    |     提现金额                        |
| status   |   Int    |    是    |     提现状态，1-处理中，2-处理中，3-提现成功，4-提现失败         |
| created  |   long   |    是    |                        提现时间                        |
| describe |  String  |    是    |                        提现说明                        |



### 3.9 赠送密码操作(设置，修改，验证)之前，获取密码状态

- 请求地址：auth/user/pre/pwd
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

* 请求结果示例：

```js
{
    errCode:0,
    data:{
    	status:1, // 是否设置过密码 1 已设置过 2 未设置过
        s1:"34546545324234214324" // 随机数字
    }
}
```



### 3.10 设置赠送密码（需要先请求3.9）

- 请求地址：auth/user/set/pwd
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| de | string | 是 | 无 | 密码加密之后的字符串 |
| s2 | string | 是 | 无 | 随机字符串 |

* 加密规则说名

```js
String apiKey; // 接口签名的apiKey
String pwd;    // 原密码MD5 32位大写
String s1;     // 3.9中返回的随机数字
String s2;   // 客户端生成的随机字符串
// 拼接密码
pwd = String.format("%s@%s", pwd, Utils.toMD5(String.format("%spinjie%s", s2, s1)));
// 拼接加密的密钥
int len = apiKey.length();
// 取出随机数字的第一位
int first = s1.charAt(0) - 48;
// 去除apiKey中的第first位
String seed = String.format("%s%s%s%s", s2, apiKey.substring(0, first),
apiKey.substring(first < len ? first + 1 : first, len), s1);
// 使用AES256加密
String de = AESUtils.encrypt(seed, pwd);
```


### 3.11 修改密码之前校验用户输入的原密码(需要先请求3.9)

- 请求地址：auth/user/change/pwd/verify
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| de | string | 是 | 无 | 密码加密之后的字符串(与3.10中的加密规则一致) |
| s2 | string | 是 | 无 | 随机字符串 |

* 请求结果示例

```js
{
	errCode:1,
    data:{
    	key:"dafasfas-fdsfdsfdsf-fsdfsdfsd-fe" // 用作修改提交新密码时的惭怍
        s1:"32436567576575" // 用户3.12中修改密码
    }
}
```

### 3.12 修改密码(需要先请求3.9)

- 请求地址：auth/user/change/pwd
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| de | string | 是 | 无 | 密码加密之后的字符串 (与3.10中的加密规则一致) |
| key | string | 是 | 无 | 3.11中返回的key |
| s2 | string | 是 | 无 | 随机字符串 |

* 请求结果示例


### 3.13 钱包收益记录

- 请求地址：auth/v1/wallet/earn_record
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| pageNum | int | 是 | 1 | 分页 |
| pageSize | int | 是 | 14 | 分页大小 |
| starting | long | 否 | 无 | 开始时间 |
| ending | long | 否 | 无 | 截止时间 |

* 请求结果示例
```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531204687607, 
    "data": [
        {
            "id": 1, 
            "userId": 1, 
            "explain": "寄售台物品购买", 
            "amount": 100, 
            "targetId": 2, 
            "type": 6, //记录类型 1-充值，2-商品折现，3-虚拟物品折现，4-寄售台出售物品，5-购买商品，6-寄售台物品购买,7虚拟物品购买
            "updated": 1530673527000, 
            "created": 1530673527000
        }, 
        {
            "id": 2, 
            "userId": 1, 
            "explain": "寄售台物品购买", 
            "amount": -999, 
            "targetId": 2, 
            "type": 6, 
            "updated": 1530673832000, 
            "created": 1530673832000
        }
    ]
}
```

### 3.14 找回赠送密码（需要先请求3.9）

- 请求地址：auth/user/find/pwd
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
|phone|string|是|无|电话号码|
|code|string|是|无|短信验证码|
| de | string | 是 | 无 | 密码加密之后的字符串 |
| s2 | string | 是 | 无 | 随机字符串 |

* 加密规则说名

```js
String apiKey; // 接口签名的apiKey
String pwd;    // 原密码MD5 32位大写
String s1;     // 3.9中返回的随机数字
String s2;   // 客户端生成的随机字符串
// 拼接密码
pwd = String.format("%s@%s", pwd, Utils.toMD5(String.format("%spinjie%s", s2, s1)));
// 拼接加密的密钥
int len = apiKey.length();
// 取出随机数字的第一位
int first = s1.charAt(0) - 48;
// 去除apiKey中的第first位
String seed = String.format("%s%s%s%s", s2, apiKey.substring(0, first),
apiKey.substring(first < len ? first + 1 : first, len), s1);
// 使用AES256加密
String de = AESUtils.encrypt(seed, pwd);
```

### 3.15 提现--修改用户银行卡/修改支付宝账号

- 请求地址：auth/v1/bankCard/update
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 |  参数说明  |
| :----------: | :------: | :------: | :----: | :--------: |
| access_token |  string  |    是    |   无   |  访问令牌  |
|id|int|是|无|用户银行id|
| depositBank  |  string  |    否    |   无   | 开户行名称/电话号码 |
|  cardHolder  |  string  |    否    |   无   | 持卡人姓名/收款人姓名 |
|    cardNo    |  string  |    否    |   无   |  银行卡号/支付宝账号 |
|    bankId    |   Int    |    否    |   无   |   银行id(修改支付宝没有bankId)  |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "msg": "修改成功",
    "time": 1528773060163
}
```


## 4. 全国行政区查询

### 4.1 获取行政区列表

- 请求地址：/auth/district/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 |             参数说明              |
| :----------: | :------: | :------: | :----: | :-------------------------------: |
| access_token |  string  |    是    |   无   |             访问令牌    |
|level|string|是|无|行政区级别 province city district|
|pAdcode|int|是|无|父级行政区id

请求结果示例：

```json
JSON：
{
    "errCode": 0, 
    "result": 0, 
    "time": 1528706361701, 
    "data": [
        {
            "adcode": 130100, 
            "pAdcode": 130000, 
            "name": "石家庄市", 
            "type": null, 
            "level": "city", 
            "created": 1528275769000, 
            "updated": 1528275769000
        }, 
        {
            "adcode": 130200, 
            "pAdcode": 130000, 
            "name": "唐山市", 
            "type": null, 
            "level": "city", 
            "created": 1528275744000, 
            "updated": 1528275744000
        }
    ]
}

```

### 4.2 获取行政区列表

- 请求地址：/auth/district/all_list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 |             参数说明              |
| :----------: | :------: | :------: | :----: | :-------------------------------: |
| access_token |  string  |    是    |   无   |             访问令牌    |

请求结果示例：

```json
JSON：
{
    "errCode": 0, 
    "result": 0, 
    "time": 1528706361701, 
    "data": [
        {
      "id": 530000,
      "name": "云南省",
      "children": [
        {
          "id": 530100,
          "name": "昆明市",
          "children": [
            {
              "id": 530102,
              "name": "五华区",
              "children": []
            },
            {
              "id": 530103,
              "name": "盘龙区",
              "children": []
            },
            {
              "id": 530111,
              "name": "官渡区",
              "children": []
            },
            {
              "id": 530112,
              "name": "西山区",
              "children": []
            }
          ]
        }
      ]
}
```

## 5. 我要--收货地址

### 5.1 收货地址列表

- 请求地址：auth/v1/reAddress/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1543476763886, 
    "data": [
        {
            "id": 16, 
            "consigneeName": "李", 
            "phone": "15752400657", 
            "addressDetail": "南屏街", 
            "address": "云南省昆明市五华区", 
            "status": 1，
            "adcode":530102
        }, 
        {
            "id": 1, 
            "consigneeName": "小明", 
            "phone": "13965869852", 
            "addressDetail": "云南省昆明市五华区一二一大街126号1单元001", 
            "address": "云南省昆明市五华区", 
            "status": 1，
            "adcode":530102
        }
    ]
}
```

| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| id | Int | 是 |  用户收货地址id |
| consigneeName | String | 是 | 收货人 |
| phone | String | 是 | 联系电话 |
| addressDetail | String | 是 | 收货人详细地址 |
| address | String | 是 | 所在地区 |
| status | Int | 是 | 地址状态 1.不是默认地址 2.是默认地址 |
| adcode | Int | 是 | 所在地区id |

### 5.2 删除收货地址

- 请求地址：auth/v1/reAddress/del
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id |  Int  |    是    |   无   | 用户收货地址id |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "msg": "删除成功",
    "time": 1528770437468
}
```

### 5.3 新增收货地址

- 请求地址：auth/v1/reAddress/add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| address |  string  |    是    |   无   | 所在地区(需要拼接) |
| addressDetail |  string  |    是    |   无   | 收货人详细地址 |
| consigneeName |  string  |    是    |   无   | 收货人 |
| adcode |  Int  |    是    |   无   | 所在地区id |
| phone |  string  |    是    |   无   | 联系电话 |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "msg": "添加成功",
    "time": 1528770750813
}
```

### 5.4 收货地址详情

- 请求地址：auth/v1/reAddress/detail
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id |  Int  |    是    |   无   | 用户收货地址id |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "time": 1528771205640,
    "data": {
        "id": 2,
        "consigneeName": "小明2",
        "phone": "13965869852",
        "addressDetail": "云南省昆明市五华区一二一大街126号1单元001",
        "address": "云南省昆明市五华区",
        "adcode": 530102
    }
}
```

| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| id | Int | 是 |  用户收货地址id |
| consigneeName | String | 是 | 收货人 |
| phone | String | 是 | 联系电话 |
| addressDetail | String | 是 | 收货人详细地址 |
| address | String | 是 | 所在地区 |
| adcode | Int | 是 | 所在地区id |

### 5.5 修改收货地址

- 请求地址：auth/v1/reAddress/update
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id |  Int  |    是    |   无   | 用户收货地址id |
| address |  string  |    是    |   无   | 所在地区(需要拼接) |
| addressDetail |  string  |    是    |   无   | 收货人详细地址 |
| consigneeName |  string  |    是    |   无   | 收货人 |
| adcode |  Int  |    是    |   无   | 所在地区id |
| phone |  string  |    是    |   无   | 联系电话 |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "msg": "修改成功",
    "time": 1528771551355
}
```

### 5.6 设置默认收货地址

- 请求地址：auth/v1/reAddress/setDefault
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id |  Int  |    是    |   无   | 用户收货地址id |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "msg": "设置成功",
    "time": 1528771835184
}
```



## 6. 商城

### 6.1 文章列表

- 请求地址：noauth/v1/article/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
|type|byte|是|无|文章类型 1-banner,2-星座，3-首页普通文章，4-商城普通文章|
| pageNum |  Int  |    否    |   1   | 分页 |
| pageSize |  Int  |    否    |   14   | 分页大小 |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "time": 1528776054880,
    "data": [
        {
            "id": 2,
            "title": "美丽的风景2",
            "summary": "这是关于一篇美丽的风景的报道2",
            "cover": "http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132",
            "created": 1528769929000,
            "type":1,
            "showTime":1547143931000
        },
        {
            "id": 3,
            "title": "美丽的风景3",
            "summary": "这是关于一篇美丽的风景的报道3",
            "cover": "http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132",
            "created": 1528769929000,
            "type":3,
            "showTime":1547143931000
        }
    ]
}
```

| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| id | Int | 是 |  文章id |
| title | String | 是 | 文章标题 |
| summary | String | 是 | 文章简介 |
| cover | String | 是 | 文章封面 |
| created | long | 是 | 创建时间 |
|type|byte|是|文章类型 1-banner,2-星座，3-首页普通文章，4-商城普通文章|
|showTime|date|是|展示时间（一般情况banner文章才有展示时间）|

### 6.2 文章详情

- 请求地址：noauth/v1/article/detail
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| id |  Int  |    是    |   无   | 文章id |

请求结果示例：

```json
{
  "errCode": 0,
  "result": 0,
  "time": 1547089258347,
  "data": {
    "id": 1,
    "title": "养不起，马来西亚考虑提前归还大熊猫？",
    "cover": "https://liyuquan.cn/static/item/181219/69455f6d-5d97-43bb-8d08-a160d5b88f57.png",
    "created": 1545134864000,
    "detail": "https://liyuquan.cn/static/article.html?uid=1"
  }
}
```

| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| id |  Int  |    是   | 文章id |
| title | String | 是 | 文章标题 |
|cover|string|否|文章封面|
| created | long | 是 | 创建时间 |
| detail | String | 是 | 文章详情(富文本) |

### 6.3 虚拟商品分类列表

- 请求地址：noauth/v1/virItem/cateList
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy


请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "time": 1528784010629,
    "data": [
        {
            "id": 3,
            "name": "动物"
        },
        {
            "id": 2,
            "name": "水果"
        },
        {
            "id": 1,
            "name": "鲜花"
        }
    ]
}
```

| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| id | Int | 是 | 虚拟商品分类id |
| name | String | 是 | 分类名称 |

### 6.4 虚拟商品列表

- 请求地址：noauth/v1/virItem/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| id |  Int  |    是    |   无   | 虚拟商品分类id(若要查询全部则不传此值) |
| pageNum |  Int  |    否    |   1   | 分页 |
| pageSize |  Int  |    否    |   14   | 分页大小 |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "time": 1528784375324,
    "data": [
        {
            "id": 1,
            "name": "小苹果",
            "brandName": "不知名",
            "price": 2,
            "description": "象征和平的小苹果",
            "cover": "https://io.shanren.group/image/http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132"
        },
        {
            "id": 3,
            "name": "小蝴蝶",
            "brandName": "",
            "price": 4,
            "description": "执子之手与子偕老",
            "cover": "https://io.shanren.group/image/http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132"
        },
        {
            "id": 2,
            "name": "一片四叶幸运草",
            "brandName": "很知名",
            "price": 3,
            "description": "等待爱情出现会幸福",
            "cover": "https://io.shanren.group/image/http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132"
        }
    ]
}
```

| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| id | Int | 是 | 虚拟商品id |
| name | String | 是 | 虚拟商品名称 |
| brandName | String | 是 | 虚拟商品品牌名称 |
| price | decimal | 是 | 虚拟商品价格 |
| description | String | 是 | 描述 |
| cover | String | 是 | 图片 |

### 6.5 主题列表

- 请求地址：noauth/v1/theme/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "time": 1529474847112,
    "data": [
        {
            "id": 1,
            "name": "父亲节",
            "cover": "http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132"
        },
        {
            "id": 2,
            "name": "暑假精品",
            "cover": "http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132"
        }
    ]
}
```

| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| id | Int | 是 | 主题id |
| name | String | 是 | 主题名称 |
| cover | String | 是 | 主题封面 |

### 6.6 主题商品列表

- 请求地址：noauth/v1/theme/item
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| id |  Int  |    是    |   无   | 主题id |
| pageNum |  Int  |    否    |   1   | 分页 |
| pageSize |  Int  |    否    |   14   | 分页大小 |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "time": 1529475044958,
    "data": [
        {
            "itemId": 2,
            "cover": "cover.jpg",
            "title": "小黄人",
            "price": 50
        },
        {
            "itemId": 3,
            "cover": "cover.jpg",
            "title": "绿巨人",
            "price": 100
        },
        {
            "itemId": 1,
            "cover": "cover.jpg",
            "title": "战国",
            "price": 600
        }
    ]
}
```

| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| itemId | Int | 是 | 商品id |
| title | String | 是 | 商品标题 |
| cover | String | 是 | 商品封面 |
| price | decimal | 是 | 商品价格 |



### 6.7 商品分类列表

- 请求地址：noauth/item/category_list
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin
- 无参数

请求结果示例：

```json
{
  "errCode": 0,
  "result": 0,
  "time": 1554862357096,
  "data": [
    {
      "id": 6,  
      "pid": 0,  
      "name": "玩具玩偶",
      "status": 1,
      "sort": 0,
      "icon": "",
      "created": 1548866372000,
      "updated": 1548866372000,
      "children": []
    },
    {
      "id": 7,  //分类id 
      "pid": 0,  //父级id
      "name": "护肤品",//分类名称
      "status": 1, //状态1正常
      "sort": 0,  //排序
      "icon": "",
      "created": 1548866381000, //创建时间
      "updated": 1548866381000,//更新时间
      "children": [  //二级分类
        {
          "id": 14,
          "pid": 7,
          "name": "雅诗兰黛",
          "status": 1,
          "sort": 0,
          "icon": "",
          "created": 1554830638000,
          "updated": 1554830641000
        },
        {
          "id": 15,
          "pid": 7,
          "name": "兰蔻",
          "status": 1,
          "sort": 0,
          "icon": "",
          "created": 1554830686000,
          "updated": 1554830689000
        }
      ]
    }
  ]
}
```



### 6.8 商品列表查询

- 请求地址：noauth/item/item_list
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

| 参数名称 | 参数类型 | 是否必传 | 默认值 |            参数说明             |
| :------: | :------: | :------: | :----: | :-----------------------------: |
| classes  |   int    |    否    |   0    | 查询类型 0-默认，1-精选，2-热门，3-新品 |
|  gender  |   int    |    否    |   0    |   筛选性别 0-默认，1-男，2-女   |
|  minAge  |   int    |    否    |   无   |            最小年龄             |
|  maxAge  |   int    |    否    |   无   |            最大年龄             |
| minPrice |  double  |    否    |   无   |            最低价格             |
| maxPrice |  double  |    否    |   无   |            最高价格             |
| eventId  |   int    |    否    |   无   |             事件id              |
| pageNum  |   int    |    否    |   1    |              分页               |
| pageSize |   int    |    否    |   14   |            分页大小             |
| categoryId |   int    |    否    |   无   |            商品分类Id             |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1528968708411, 
    "data": [
        {
            "itemId": 1, 
            "cover": "cover.jpg", 
            "title": "战国", 
            "price": 600
        }, 
        {
            "itemId": 3, 
            "cover": "cover.jpg", 
            "title": "绿巨人", 
            "price": 100
        }
    ]
}
```

### 6.9 商品详情

- 请求地址：noauth/item/item_detail
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    否    |   无   | 访问令牌 |
|      id      |   Int    |    是    |   无   |  商品id  |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "time": 1528876600866,
    "data": {
        "id": 2,
        "title": "小黄人",
        "price": 50,
        "sales": 1,
        "description": "秦国质子",
        "status": 1,
        "pictures": [
            "cover.jpg",
            "http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132"
        ],
        "detailUrl": "https://43.241.223.169:800/static/product.html?id=2",
        "isCollect": 2
    }
}
```

| 参数名称    |   参数类型   | 是否必传 |          参数说明          |
| ----------- | :----------: | :------: | :------------------------: |
| errCode     |     Int      |    是    | 错误码 0 标识成功获取数据  |
| data        |    Object    |    否    |        成功返回数据        |
| id          |     Int      |    是    |           商品id           |
| title       |    String    |    是    |          商品标题          |
| price       |   decimal    |    是    |          商品价格          |
| sales       |     Int      |    是    |            销量            |
| status       |     Int      |    是    |            状态1-正常，2-下架   |
| description |    String    |    是    |          商品属性          |
| pictures    | List<String> |    是    |        轮播图片数组        |
| detailUrl   |    String    |    是    |        商品详情地址        |
| isCollect   |     Int      |    是    | 是否收藏 1.已收藏 2.未收藏 |

### 6.10 商品评论列表

- 请求地址：noauth/item/comment_list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
|    id    |   Int    |    是    |   无   |  商品id  |
| pageNum  |   Int    |    否    |   1    |   分页   |
| pageSize |   Int    |    否    |   14   | 分页大小 |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "time": 1528882817550,
    "data": [
        {
            "created": 1528882416000,
            "phone": "13908867120",
            "avatar": "avatar.jpg",
            "nickname":"额",
            "userId": 4,
            "content": "22342",
            "pictures": [
                "cover.jpg",
                "http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132"
            ],
            "star": 0
        }
    ]
}
```

| 参数名称 |   参数类型   | 是否必传 |         参数说明          |
| -------- | :----------: | :------: | :-----------------------: |
| errCode  |     Int      |    是    | 错误码 0 标识成功获取数据 |
| data     |    Object    |    否    |       成功返回数据        |
| phone    |    String    |    是    |         用户电话          |
| avatar   |    String    |    是    |         用户头像          |
|nickname|String|是|用户昵称|
|userId|Int|是|用户id|
| content  |    String    |    是    |         评论文字          |
| pictures | List<String> |    是    |       评论图片数组        |
| created  |     Long     |    是    |         评论时间          |
| star     |     Int      |    是    |         评价分数          |

### 6.11 收藏商品/取消收藏商品

- 请求地址：auth/v1/favorite/isCollection
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
|      id      |   Int    |    是    |   无   |  商品id  |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "msg": "收藏成功",
    "time": 1528878654372
}
```

```json
{
    "errCode": 0,
    "result": 0,
    "msg": "取消收藏成功",
    "time": 1528878689795
}
```

### 6.12 我要--收藏列表

- 请求地址：auth/v1/favorite/itemList
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "time": 1528879717000,
    "data": [
        {
            "id": 5,
            "itemId": 1,
            "title": "异人",
            "status": 1,
            "cover": "cover.jpg",
            "price": 600
        },
        {
            "id": 7,
            "itemId": 2,
            "title": "小黄人",
            "status": 1,
            "cover": "cover.jpg",
            "price": 50
        }
    ]
}
```

| 参数名称 | 参数类型 | 是否必传 |         参数说明          |
| -------- | :------: | :------: | :-----------------------: |
| errCode  |   Int    |    是    | 错误码 0 标识成功获取数据 |
| data     |  Object  |    否    |       成功返回数据        |
| id       |   Int    |    是    |        收藏商品id         |
| itemId   |   Int    |    是    |          商品id           |
| status       |     Int      |    是    |            状态1-正常，2-下架   |
| title    |  String  |    是    |         商品标题          |
| price    | decimal  |    是    |         商品价格          |
| cover    |  String  |    是    |         商品封面          |

### 6.13 我要--批量删除收藏列表

- 请求地址：auth/v1/favorite/delItem
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 |          参数说明           |
| :----------: | :------: | :------: | :----: | :-------------------------: |
| access_token |  string  |    是    |   无   |          访问令牌           |
|     ids      |  string  |    是    |   无   | 收藏商品id集合(用逗号,隔开) |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "msg": "删除成功",
    "time": 1528880466970
}
```
### 6.14 商品搜索

- 请求地址：noauth/item/search
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 |          参数说明           |
| :----------: | :------: | :------: | :----: | :-------------------------: |
| keyword |  string  |    是    |   无   |           关键字          |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531117210397, 
    "data": [
        {
            "itemId": 2, 
            "cover": "https://io.shanren.group/image/cover.jpg", 
            "title": "小黄人小黄人小黄人小黄人小黄人小黄人小黄人", 
            "price": 50
        }
    ]
}
```

### 6.15 创建虚拟商品订单
- 请求地址：auth/v1/virOrder/create
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
|  access_token  |   String    |    是    |   无   |  访问令牌  |
| id | int | 是 | 无 | 虚拟商品id |
| quantity | int | 是 | 无 | 商品数量 |

请求结果示例：
```json
{
    "errCode": 0,
    "result": 0,
    "time": 1542788619794,
    "data": {
        "orderNo": 318112116111,// 订单号
        "totalPrice": 6 // 总价格
    }
}
```

### 6.16 虚拟商品订单支付
- 请求地址：auth/v1/virOrder/pay
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
|  access_token  |   String    |    是    |   无   |  访问令牌  |
| orderNo | Long | 是 | 无 | 订单号 |
| payWay | int | 是 | 无 | 支付方式，微信 24656 ，支付宝 78990，余额98001 ，87661 礼花|

请求结果示例：
```json
{
     "errCode": 0,
     "result": 0,
     "time": 1542788894098,
     "data": {
         "params": "elDbeJM/bdEJxxZIU0tNsKZTTbs/s337/0TujqrP6fNrFz0Jyoi+503FLW5ZO13AGWh4U1glI85Tjt9HRkygwv9CGQ0FEDpYYi6Ln/p3LZg61yi7YvBLtV24BpQXt51mGPm8xkrA3SCL+j0ZuX6I30M8/L7EJSTaP5ROLgZmAQuKsq5knHNH+Hsnwv/q3TiaI0uuCg6iviqSAdsNN+5ZVdcAapm0tnDPdbE+tpt0FhhAiVXldLdus/AOrWH+pDQEQ1h3r6pjSz26Vua3/rIba9oGYRMiPViNS9sXLe1bvxOUaGtKdFMBjonH+fka6yv7sIWdzlmE+RKgXptd+yAKMg==f0GBsDoc8v036/4uT24pVky5rEQM7x8pkM7hfrw+QrvU8SCdD4QR56lS2krd/ROvbNxllW7iM1c1A1NpbxfNDsOmOwXS0W4tDfyFm1dNWgIefe8KstUsF4SVeLFvDeL7tb6lkbo035e4cuhwNRSPnO/g0lZOC0FICEBLrUaIaKypz10ix8tO7w9aIWrU8VpiOMCdcSmu49w8ylBgWmK1DcqWpx2ukWnTDOiIfwJMsRVUm58OU7hUf0CHpEKlR0xZ2BC2wqWbZ0Aulq97rIWPdaQXmTRhz+G7v0Lw6gzh2u53HLo4b8PFfxKqg6O0xVzmyYiOnhOC+q+KJGKXvvbNsQ==||344,344",
         "orderNo": 318112116110,
         "type": 24656
     }
 }
```

### 6.17 获取sku库存
- 请求地址：noauth/item/sku_stock
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
|  skuId  |   int    |    是    |   无   |  商品最小销售单元id  |


请求结果示例：
```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1543754570763, 
    "data": {
        "id": 456,   //skuid 
        "itemId": 4,   //商品id
        "title": "蜘蛛人",  //sku标题
        "cover": "https://liyuquan.cn/staticcover.jpg",  //封面图
        "pictures": "[\"cover.jpg\"]",  //相册图
        "stock": 0,  //库存
        "price": 0,  //sku价格
        "sales": 0,  //sku销售量
        "status": 2,  //状态 1 正常 2 禁用
        "created": 1532508987000,  //创建时间
        "updated": 1532508987000  //更新时间
    }
}
```


### 6.18 根据sku获取商品详情
- 请求地址：noauth/item/detail
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
|  skuId  |   int    |    是    |   无   |  商品最小销售单元id  |


请求结果示例：
```json
{
  "errCode": 0,
  "result": 0,
  "time": 1545833343828,
  "data": {
    "id": 14,
    "title": "HELLO KITTY HEART手链1",
    "price": 688.00,
    "sales": 0,
    "description": "HELLO KITTY HEART手链浪漫粉红精致尚",
    "pictures": [
      "https://liyuquan.cn/static/item/181219/a37beac0-d763-4e39-a1ad-1744836004fd.jpg",
      "https://liyuquan.cn/static/item/181219/2f333aa8-fdae-4f33-a344-00e45594a7df.jpg",
      "https://liyuquan.cn/static/item/181219/fe96d4bd-362e-4a68-abdf-3d2b0bbd66ab.jpg",
      "https://liyuquan.cn/static/item/181219/4dc1b105-e85c-4564-bcd3-0cbca0daa165.jpg",
      "https://liyuquan.cn/static/item/181219/33a12e8e-5a69-455a-9ff5-4f6f91e94606.jpg"
    ],
    "detailUrl": "https://liyuquan.cn/static/product.html?uid=14",
    "isCollect": 2 ,
    "status": 1 
  }
}

```


| 参数名称    |   参数类型   | 是否必传 |          参数说明          |
| ----------- | :----------: | :------: | :------------------------: |
| errCode     |     Int      |    是    | 错误码 0 标识成功获取数据  |
| data        |    Object    |    否    |        成功返回数据        |
| id          |     Int      |    是    |           商品id           |
| title       |    String    |    是    |          商品标题          |
| price       |   decimal    |    是    |          商品价格          |
| sales       |     Int      |    是    |            销量            |
| description |    String    |    是    |          商品属性          |
| pictures    | List<String> |    是    |        轮播图片数组        |
| detailUrl   |    String    |    是    |        商品详情地址        |
| isCollect   |     Int      |    是    | 是否收藏 1.已收藏 2.未收藏 |
|status|int|是|1 正常 2 下架|

### 6.19 文章商品列表
- 请求地址：noauth/v1/article/item_list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
|  id  |   int    |    是    |   无   |  文章id  |
|pageNum|int|否|1|分页|
|pageSize|int|否|14|分页大小

请求结果示例：
```json
{
  "errCode": 0,
  "result": 0,
  "time": 1547105455053,
  "data": [
    {
      "itemId": 7,
      "cover": "https://liyuquan.cn/static/item/181218/a651139d-95bc-420f-947b-7de678afaed2.jpg",
      "title": "CRYSTALDUST手镯女士多层手环",
      "price": 199.00
    },
    {
      "itemId": 8,
      "cover": "https://liyuquan.cn/static/item/181219/217544e5-c3f0-4bb6-82ea-251810ab24ae.jpg",
      "title": "CRYSTALLINE经典款学生用圆珠笔",
      "price": 100.00
    },
    {
      "itemId": 9,
      "cover": "https://liyuquan.cn/static/item/181219/d46de363-8ad8-4453-ba9b-6165311e682e.jpg",
      "title": "DUO 恶魔之眼耳环 女生个性神秘耳钉耳饰",
      "price": 200.00
    }
  ]
}

```
| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| itemId | Int | 是 | 商品id |
| title | String | 是 | 商品标题 |
| cover | String | 是 | 商品封面 |
| price | decimal | 是 | 商品价格 |



### 6.20 商城标签列表

- 请求地址：noauth/v1/label/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |

请求结果示例：

```json
{
  "errCode": 0,
  "result": 0,
  "time": 1547111061028,
  "data": [
    {
      "id": 1,
      "name": "送朋友",
      "cover": "/item/181219/6dfffbeb-0fb8-4bce-9528-a898cccb1373.png",
      "sort": 1,
      "status": 1,
      "created": 1547081901000,
      "updated": 1547081903000
    },
    {
      "id": 2,
      "name": "送家人",
      "cover": "/item/181219/6dfffbeb-0fb8-4bce-9528-a898cccb1373.png",
      "sort": 2,
      "status": 1,
      "created": 1547081922000,
      "updated": 1547081925000
    }
  ]
}

```

| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| id | Int | 是 | 标签id |
| name | String | 是 | 标签名称 |
| cover | String | 是 | 标签封面 |
|sort|int|否|排序值|
|status|byte|否|1正常|

### 6.21 标签商品列表

- 请求地址：noauth/v1/label/item_list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| id |  Int  |    是    |   无   | 标签id |
| pageNum |  Int  |    否    |   1   | 分页 |
| pageSize |  Int  |    否    |   14   | 分页大小 |

请求结果示例：

```json
{
  "errCode": 0,
  "result": 0,
  "time": 1547111634245,
  "data": [
    {
      "itemId": 11,
      "cover": "https://liyuquan.cn/static/item/181219/f5888c13-6b00-4e3d-a695-3f5e42683493.jpg",
      "title": "FRISSON 时尚简约戒指指环女手饰配饰",
      "price": 900.00
    },
    {
      "itemId": 10,
      "cover": "https://liyuquan.cn/static/item/181219/fac8ebae-738a-49ae-9fc6-f9127e27f700.jpg",
      "title": "DUO恶魔之眼浪漫钥匙项链锁骨链",
      "price": 200.00
    }
  ]
}
```

| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| itemId | Int | 是 | 商品id |
| title | String | 是 | 商品标题 |
| cover | String | 是 | 商品封面 |
| price | decimal | 是 | 商品价格 |


### 6.22 商品文章搜索（没用到）

- 请求地址：noauth/item/search_all
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 |          参数说明           |
| :----------: | :------: | :------: | :----: | :-------------------------: |
| keyword |  string  |    是    |   无   |           关键字          |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531117210397, 
    "data": [
      {
      "id": 159,
      "cover": "https://liyuquan.cn/static/item/190212/d310d9b8-69a3-4c9b-b828-4b0e26e2a1d1.jpg",
      "title": "dior迪奥香水小样女士真我三五件套装花漾甜心旗舰店官方正品", //标题
      "price": 165.00,  //商品价格
      "summary": "",  //文章简介
      "type": 1   //1 商品，2文章
    },
    {
      "id": 56,
      "cover": "https://liyuquan.cn/static/item/190211/65cc3408-a48b-4077-85e6-48f6be960c45.jpg",
      "title": "原铁道部长:中国高铁不是一夜花开 而是香自苦寒",
      "price": null,
      "summary": "原标题：中国高铁不是“一夜花开”，而是“香自苦寒”║原铁道部长、复兴号研制技术顾问傅志寰",
      "type": 2
    }
    ]
}
```

### 6.23 商品文章事件搜索

- 请求地址：auth/search/all_list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 |          参数说明           |
| :----------: | :------: | :------: | :----: | :-------------------------: 
| access_token |  String  |    是    |   无   | 访问令牌 |
| keyword |  string  |    是    |   无   |           关键字          |
|type|int|是|1|1商品，2 文章，3 事件|

type 等于1，2请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531117210397, 
    "data": [
      {
      "id": 159,
      "cover": "https://liyuquan.cn/static/item/190212/d310d9b8-69a3-4c9b-b828-4b0e26e2a1d1.jpg",
      "title": "dior迪奥香水小样女士真我三五件套装花漾甜心旗舰店官方正品", //标题
      "price": 165.00,  //商品价格
      "summary": "",  //文章简介
      "type": 1   //1 商品，2文章
    }
    ]
}
```

type = 3 请求结果示例：

```json
{
  "errCode": 0,
  "result": 0,
  "time": 1552374188046,
  "data": [
    {
      "id": 1,   //备忘录id
      "userId": 6, //创建者id
      "targetTime": 1552374073000, //备忘时间
      "isCirculation": 0, //0-不循环
      "detail": "晚会", //事件详情
      "type": 1,  // 1自己创建，2-被邀请，3-节日事件
      "users": "1,31", //被邀请者用户id 
      "created": 1552374107000, //创建时间
      "nickname": "林琴", //创建者用户昵称
      "name": "", //type等于1，2时没用到
      "count": 2, //邀请人数
      "avatar": "https://wx.qlogo.cn/mmopen/vi_32/cLhvDgpVNMm24pZLQn9NJLvTbribW3ymS4dXSctqaaKWhF7NJcI1Nicqp0QGw2jjVPJBLBjGStsYkaefM5fiaq5SA/132" //创建着头像
     "eventTypeId": 1  //事件类型id
      "eventTypeName": "生日" //事件类型名称
    },
    {
      "id": 1,   //type等于3时，节日事件id 
      "userId": null,
      "targetTime": 1552002895000, //备忘时间
      "isCirculation": null,  //节日事件不循环
      "detail": "女神节i，女王节，，，",  //节日事件简介
      "type": 3, //1自己创建，2-被邀请，3-节日事件
      "users": "", 
      "created": 1552262181000,
      "nickname": "",
      "name": "妇女节", //节日事件名称
      "count": null, 
      "avatar": "https://liyuquan.cn/staticcover.jpg" //节日事件标题
     "eventTypeId": null  //事件类型id
    }
    ]
}
```


## 7. 购物车

### 7.1 购物车列表

- 请求地址：auth/cart/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  String  |    是    |   无   | 访问令牌 |
| quantity |  int  |    否    |   1   | 加入购物车数量 |

请求结果示例：

```json 
{
    "errCode": 0, 
    "result": 0, 
    "time": 1528945325582, 
    "data": [
        {
            "itemId": 1, 
            "skuId": 1, 
            "cover": "cover.jpg", 
            "title": "秦孝公", 
            "quantity": 1, 
            "price": 1000, 
            "created": 1528884318000,
            "status":2,  //商品状态1-正常，2-下架
            "skuStatus":2  //商品sku状态1-正常，2-下架
        }
    ]
}
```



### 7.2 加入购物车

- 请求地址：auth/cart/add_item
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 |      参数说明      |
| :----------: | :------: | :------: | :----: | :----------------: |
| access_token |  string  |    是    |   无   |      访问令牌      |
|    skuId     |   int    |    是    |   无   | 商品最小销售单元id |
|quantity|int|是|无|商品数量|

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "加入购物车成功", 
    "time": 1528969439358
}
```



### 7.3 增加或减少商品数量

- 请求地址：auth/cart/quantity
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 |      参数说明      |
| :----------: | :------: | :------: | :----: | :----------------: |
| access_token |  string  |    是    |   无   |      访问令牌      |
|    skuId     |   int    |    是    |   无   | 商品最小销售单元id |
|    change    |   int    |    是    |   无   | 增加或减少商品数量 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1528985861537
}
```



### 7.4 删除购物车中商品

- 请求地址：auth/cart/delete
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 |      参数说明      |
| :----------: | :------: | :------: | :----: | :----------------: |
| access_token |  string  |    是    |   无   |      访问令牌      |
|    skuId     |   int    |    是    |   无   | 商品最小销售单元id（删除多个时用逗号隔开） |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "删除成功", 
    "time": 1528987108217
}
```

### 7.5 获取商品的sku组合

- 请求地址：noauth/item/sku_set
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
|  itemId  |   int    |    是    |   无   |  商品id  |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1529407687288, 
    "data": {
        "features": [
            {
                "featureId": 1, 
                "name": "爵位", 
                "values": [
                    {
                        "valueId": 1, 
                        "value": "公"
                    }, 
                    {
                        "valueId": 3, 
                        "value": "侯"
                    }
                ]
            }, 
            {
                "featureId": 2, 
                "name": "称谓", 
                "values": [
                    {
                        "valueId": 2, 
                        "value": "秦"
                    }, 
                    {
                        "valueId": 4, 
                        "value": "魏"
                    }
                ]
            }
        ], 
        "skus": [
            {
                "skuId": 1, 
                "itemId": 1, 
                "title": "秦孝公", 
                "cover": "cover.jpg", 
                "price": 1000, 
                "stock": 5, 
                "sales": 0, 
                "isDefault": 1，
                "values": [
                    {
                        "valueId": 1, 
                        "featureId": 1, 
                        "value": "公"
                    }, 
                    {
                        "valueId": 2, 
                        "featureId": 2, 
                        "value": "秦"
                    }
                ]
            }, 
            {
                "skuId": 2, 
                "itemId": 1, 
                "title": "魏文侯", 
                "cover": "cover.jpg", 
                "price": 1000, 
                "stock": 5, 
                "sales": 0, 
                "isDefault": 2，
                "values": [
                    {
                        "valueId": 3, 
                        "featureId": 1, 
                        "value": "侯"
                    }, 
                    {
                        "valueId": 4, 
                        "featureId": 2, 
                        "value": "魏"
                    }
                ]
            }
        ]
    }
}
```

| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| :---: | :---: | :---: | :---: | :---: |
| featureId | int | 是 | 商品属性id |
| name | string | 是 | 属性名称 |
| valueId | int | 是 | 商品属性值id |
| value | string | 是 | 商品属性值 |
| skuId | int | 是 | 商品最小销售单元id |
| itemId | int | 是 | 商品id |
| title | string | 是 | 商品标题 |
| cover | string | 是 | 商品封面图片 |
| price | int | 是 | 商品价格 |
| stock | int | 是 | 商品库存 |
| sales | int | 是 | 商品销量 |
|isDefault|int|是|1 默认sku,2 不是默认sku|

### 7.6 创建商品订单
- 请求地址：auth/item/order/create
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
|  access_token  |   String    |    是    |   无   |  访问令牌  |
| skus | String | 是 | 无 | json数组，数组格式，[{"skuId":skuId,"quantity":quantity}] |
| payWay | int | 是 | 无 | 支付方式，微信 24656 ，支付宝 78990，余额98001 ，87661 礼花|
|isShoppingCart|byte|是|无|是否从购物车购买 1 是 ，2 不是

请求结果示例：
```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1529639907550, 
    "data": {
        "params": "uiOEq5JisHOXjowq+bmX02bIomMwVWKGh+dmo38mbHi9vnlgj9L6nfojA2xxw9qXw2TUXi2A+BajzRKcJax6ZgGFaa3+dm0w1/4rsed8U4b+U9DX0AeveUKqvQY+1g3Pt4XsbNnoh6cr61K5B12wlp31A67n1gpSeufrYYo0ZHg2zEwwaZqxpqo3K+87b32zm9G1Pkqxyv5dpqOOzdeQbp9/HZDau+F5TG/6R5zkh51sU3+2ObRsQbdzfS5mDfuUUe7jWuHH9VeX7rY+gPgciRvt3u+3gfoeHO3BXQLG/Ni9dU9V0aEXGiApXIl9C6vuFGhD6jLtiyL0s6juASewzg==kjyGiTxGKNyOjLdNoaTPcoxPWoLVtmpBK+dvlH3FJ49ErulyY+FSiO1sxwWmz3GR1SIZsSMHN28XGMaPlgu2IAryqgisTl/YNCISwMbLfvLHC3SwIPDgyjMslAMLKsFo5pL/ZGX94+M4p1Sr26mLO+g+atRhUzyxQH1Wg8v5uBkMw8SXDrABFydeqyO+8hRpv8HdoaHd+01E4jnYg2zz3y6VmI8Sa6tWpcLTZ/ZbaDc9fxoGA1nOBjN7fMOCPtZyJtkquTtCEKok1tg0m4lzn+9WE/57MqjOamhfPygqvU8ET+K6PV/51YDV9poPVxL0OPdBksioo6efsb3ydVP61w==||344,344", 
        "orderNo": 21806221110003, 
        "type": 24656
    }
}
```
### 7.7 订单支付
- 请求地址：auth/item/order/pay
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
|  access_token  |   String    |    是    |   无   |  访问令牌  |
| orderNo | Long | 是 | 无 | 订单号 |
| payWay | int | 是 | 无 | 支付方式，微信 24656 ，支付宝 78990，余额98001 ，87661 礼花|
|isShoppingCart|byte|是|无|是否从购物车购买 1 是，2不是|

请求结果示例：
```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1529640449848, 
    "data": {
        "params": "jHWd8TC7HrEiLzosZOLAv/BFB8z8BBpInfRj5lKA/t99U/pTxeew2YVQ1zU2kec8ICdcdXor7CLppaYKhGT5hwqcrZgvlKPiUfGOsN3z0fFAJS4G9cNBpsUrcyvag93U7EEmQT8Xa9Be0HXTfCcmig7mhgIBNfuWqQWAQIkBDior4zdlSzAsZ0AJ/YubmJtcQWQhOU6/2VK4uo/uHoxo9G04emIp6+LCM9Bapku00wMeANY2UEkmbcFO5r4oZhkW5IAijNKu0bLVfp1H+s9Kh6TQwB1nhLVqFxl69nIFbmwybYprgRl3RFkZRL896bmeXkDzBVuQSrBW6qfkyl4tKQ==mkOGG3oeEEEnLMnpwdhTmlSo9wH+VI369sgyFQ0+EyG9FZG18cjxx6cU6BNvVwLwFiTIgev1e1mjDrxJJFEGFFiVF1ypzsKu+6RcFVbIU5kz9Denpu7G0h+yiQ29FwNir5hHxEW2o7Ps/mTmdxqasPZvwIvftdvk38F3dCViUAjshqdo3Esu/EA3Zmtk0uzqAmWYteqiXCQaH1zNMdrXT+ePV0d/VlSDgC0Y6OSvjBhIuidPwsmjloyHmA9JFIvcPKs1CehnvFrJiSpXi4AMzk2z8bhDWVwKieyEtRCgsOYWIBJcVKSuFh4wpoOQbt0/rZnpWfZ1hehPT9CEkUZN5w==||344,344", 
        "orderNo": 21806221110003, 
        "type": 24656
    }
}
```

### 7.8 订单取消
- 请求地址：auth/item/order/cancel
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
|  access_token  |   String    |    是    |   无   |  访问令牌  |
| orderNo | Long | 是 | 无 | 订单号 |

请求结果示例：
```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "订单取消成功", 
    "time": 1529640838802
}
```

### 7.9 订单列表
- 请求地址：auth/item/order/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
|  access_token  |   String    |    是    |   无   |  访问令牌  |
| status | int | 是 | 无 | 状态， 1-未完成（未付款），2-已完成（已付款） |
| pageNum | int | 是 | 1 | 分页 |
| pageSize | int | 是 | 14 | 分页大小 |

请求结果示例：
```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1530156796210, 
    "data": [
        {
            "id": 105, 
            "userId": 1, 
            "storeId": null, 
            "orderNo": 220180610008, 
            "totalPrice": 4000, 
            "quantity": 4, 
            "status": 1, 
            "created": 1529568689000, 
            "updated": 1529568689000, 
            "detailVos": [
                {
                    "itemId": 1, 
                    "skuId": 1, 
                    "title": "秦孝公", 
                    "price": 1000, 
                    "cover": "https://io.shanren.group/image/cover.jpg", 
                    "quantity": 2,
                    "payPlatform": 98001
                }, 
                {
                    "itemId": 1, 
                    "skuId": 2, 
                    "title": "魏文侯", 
                    "price": 1000, 
                    "cover": "https://io.shanren.group/image/cover.jpg", 
                    "quantity": 2,
                    "payPlatform": 98001
                }
            ]
        }, 
        {
            "id": 106, 
            "userId": 1, 
            "storeId": null, 
            "orderNo": 220180610009, 
            "totalPrice": 4000, 
            "quantity": 4, 
            "status": 1, 
            "created": 1529568889000, 
            "updated": 1529568889000, 
            "detailVos": [
                {
                    "itemId": 1, 
                    "skuId": 1, 
                    "title": "秦孝公", 
                    "price": 1000, 
                    "cover": "https://io.shanren.group/image/cover.jpg", 
                    "quantity": 2,
                    "payPlatform": 98001
                }, 
                {
                    "itemId": 1, 
                    "skuId": 2, 
                    "title": "魏文侯", 
                    "price": 1000, 
                    "cover": "https://io.shanren.group/image/cover.jpg", 
                    "quantity": 2,
                    "payPlatform": 98001
                }
            ]
        }
    ]
}
```
| 参数名称 | 参数类型 | 是否必传 |  参数说明 |
| :------: | :------: | :------:  | :------: |
|  userId  |   int    |    是    | 用户Id  |
| storeId | int | 否 | 店铺Id |
| orderNo | Long | 是 | 订单号 |
| totalPrice | float | 是 | 商品总价 |
| quantity | int | 是 | 商品数量 |
| itemId | int | 是 | 商品id |
| skuid | int | 是 | 最小销售单元id |
| title | string | 是 | 商品标题 |
| price | int | 是 | 商品单价 |
| cover | string | 是 | 商品封面图片 |
|status|int|是|订单状态1-未付款，2-已付款，3-已取消，4-已删除|
| payPlatform | int | 是 | 支付方式，微信 24656 ，支付宝 78990，余额98001 ，87661 礼花 |

## 8 图片上传

### 8.1 base64上传

- 请求地址：noauth/file/upload
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
|  base64  |   String    |    是    |   无   |  图片base64编码  |
| module | int | 否 | 无 | 模块（目前传1） |


### 8.2 multipart方式上传

- 请求地址：noauth/file/upload/multi
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
| module | int | 否 | 无 | 模块（目前传1） |

## 9. 我要--商家认证

### 9.1 商家认证状态

- 请求地址：auth/v1/merchant/status
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "time": 1529481177636,
    "data": {
        "status": 0
    }
}
```

| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| status | Int | 是 |  认证状态 0.未认证 2 审核中 3 审核通过 4 审核失败 |

### 9.2 商家认证状态

- 请求地址：auth/v1/merchant/apply
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| name |  string  |    是    |   无   | 企业名称 |
| address |  string  |    是    |   无   | 企业地址 |
| registrationNo |  string  |    是    |   无   | 企业注册号 |
| legalPerson |  string  |    否    |   无   | 法人代表 |
| phone |  string  |    是    |   无   | 联系电话 |
| licensePic |  string  |    是    |   无   | 营业执照照片 |
| otherPics |  string  |    是    |   无   | 其他证件图片 |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "msg": "申请成功",
    "time": 1529481533568
}
```

## 10 通讯录

### 10.1 添加好友

- 请求地址：auth/friend/add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| targetUserId | String | 是 | 无 | 要添加为好友的用户id |
| validationMsg | string | 否 | 无 | 验证信息 |
| remark | string | 否 | 无 | 备注名称 |
| groupId | int | 否 | 无 | 分组id |

* 请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    msg: "已发送好友申请通知", 
    time: 1529558250434
}
```

### 10.2 获取好友验证消息列表

- 请求地址：auth/friend/notifyMsgs
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| pageNum | int | 否 | 1 | 分页 |
| pageSize | int | 否 | 14 | 分页大小 |

* 请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    time: 1529562687763, 
    data: [
        {
            msgId: 6, 
            userId: 4, 
            targetUserId: 1, 
            type: 2, 
            status: 1, 
            validationMsg: "么么哒", 
            reply: "", 
            avatar: "avatar.jpg", 
            nickname: "什么"
        }, 
        {
            msgId: 5, 
            userId: 4, 
            targetUserId: 1, 
            type: 1, 
            status: 1, 
            validationMsg: "么么哒", 
            reply: "", 
            avatar: "avatar.jpg", 
            nickname: "什么"
        }, 
        {
            msgId: 4, 
            userId: 4, 
            targetUserId: 1, 
            type: 1, 
            status: 1, 
            validationMsg: "", 
            reply: "", 
            avatar: "avatar.jpg", 
            nickname: "什么"
        }
    ]
}
```

| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| :---: | :---: | :---: | :---: | :---: |
| msgId | int | 是 | 消息id |
| targetUserId | int | 是 | 目标用户id |
| type | int | 是 | 消息类型 1 我请求加好友的消息 2 别人请求加我为好有的消息 |
| status | int | 是 | 消息的状态 1 待验证 （type==2需要显示同意、拒绝、回复按钮） 2 已同意 3 已拒绝 4 已回复 |
| validationMsg | string | 是 | 验证消息 |
| reply | string | 是 | 回复消息 |
| avatar | string | 是 | 显示的头像 |
| nickname | string | 是 | 显示的昵称 |


### 10.3 验证消息操作（同意、拒绝、回复好友申请）

- 请求地址：auth/friend/opt
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| opt | int | 是 | 无 | 1 同易 2 拒绝 3 回复 |
| reply | String | 否 | 无 | opt=3时必传 |

* 请求结果示例

```js
{
	errCode:0,
    msg:"操作成功!"
}
```


### 10.4 添加分组

- 请求地址：auth/friend/group/add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| groupName | String | 是 | 无 | 分组名称 |

* 请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    time: 1529571081385, 
    data: {
        id: 101,  // 新增的分组id
        userId: 4, 
        name: "沉鱼", 
        sort: 0, 
        created: null, 
        updated: null
    }
}
```

### 10.5 删除分组

- 请求地址：auth/friend/group/del
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| groupId | int | 是 | 无 | 分组id |

* 请求结果示例

```js
{
    errCode: 0
}
```

### 10.6 修改分组的名称

- 请求地址：auth/friend/group/modify
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| groupId | int | 是 | 无 | 分组id |
| groupName | String | 是 | 无 | 分组名称 |

* 请求结果示例

```js
{
    errCode: 0
}
```


### 10.7 修改用户的分组、备注、关系信息

- 请求地址：auth/friend/modify
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| groupId | int | 否 | 无 | 分组id |
| friendUserId | int | 是 | 无 | 好友用户id |
| remark | string | 否 | 无 | 备注 |
| relationship | string | 否 | 无 | 关系 |

* 请求结果示例

```js
{
    errCode: 0
}
```


### 10.8 分组列表

- 请求地址：auth/friend/group/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
|isAll|int|否|1|是否显示全部 1 不显示（默认），2 显示全部|

* 请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    time: 1529571867406, 
    data: [
        {
            id: 0, 
            userId: 4, 
            name: "未分组", 
            sort: 0, 
            created: null, 
            updated: null
        }, 
        {
            id: 101, 
            userId: 4, 
            name: "沉鱼", 
            sort: 0, 
            created: 1529571080000, 
            updated: 1529571080000
        }
    ]
}
```


### 10.9 好友列表

- 请求地址：auth/friend/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| groupId | int | 是 | 无 | 分组id（不传获取所有好友） |

* 请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    time: 1529573443053, 
    data: [
        {
            id: 104, 
            userId: 4, 
            friendUserId: 1, 
            remark: "",  // 备注名
            relationship: "",  // 和我的关系
            groupId: 101, 
            sort: 0, 
            created: 1529568102000, 
            updated: 1529568102000, 
            avatar: "avatar.jpg", 
            nickname: "什么", 
            heartNum: 0 // 心
        }
    ]
}
```

### 10.10 删除好友

- 请求地址：auth/friend/del
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| friendUserId | int | 是 | 无 | 好友用户id |

* 请求结果示例



### 10.11 搜索好友

- 请求地址：auth/friend/search
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| key | string | 是 | 无 | 搜索关键字，支持手机号和昵称 |
|type|int|否|无|1 搜索已加好友， 2 搜索陌生人， 不传 ：搜索所有|

* 请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    time: 1529590714779, 
    data: {
    	[
            userId: 1, 
            avatar: "avatar.jpg", 
            nickname: "什么", 
            isFriend: 1 // 是否是好友 1 是 2 不是
        ]
    }
}
```



### 10.12 新的好友消息未查看数量（通讯录红点）

- 请求地址：auth/friend/count
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |


* 请求结果示例

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1547199348380,
  "data": {
    "messageCount": 0,  //未查看的消息数量
  }
}
```


### 10.13 给好友留言

- 请求地址：auth/leaveMessage/add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
|friendUserId|int|是|无|好友id（被留言者）|
|detail|string|是|无|留言内容|


* 请求结果示例

```js
{
  "errCode": 0,
  "result": 0,
  "msg": "留言成功",
  "time": 1553067029023
}

```

### 10.14 微信邀请好友（小程序添加好友）

- 请求地址：auth/friend/wx_add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 被邀请者token |
|targetUserId|int|是|无|邀请者用户id|


* 请求结果示例

```js
{
  "errCode": 0,
  "result": 0,
  "msg": "添加成功",
  "time": 1553067029023
}

```


## 11 备忘录（没在用，请看备忘录2.0（22））

### 11.1 添加活动（目前没有用到）

- 请求地址：auth/memo/activity/add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| targetTime | long | 是 | 无 | 备忘时间（13位时间戳） |
| address | string | 是 | 无 | 地点 |
| count | int | 是 | 无 | 人数 |
| users | string | 是 | 无 | 邀请的好友的user id集合，多个用,隔开 |
| title | string | 是 | 无 | 标题 |
| detail | string | 是 | 无 | 活动详情 |

* 请求示例

```js
{
	"errCode": 0,
	"result": 0,
	"msg": "添加成功!",
	"time": 1529643310344
}
```



### 11.2 添加事件（目前没有用到）

- 请求地址：auth/memo/event/add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| targetTime | long | 是 | 无 | 备忘时间（13位时间戳） |
| eventTime | long | 是 | 无 | 事件时间 |
| detail | string | 是 | 无 | 活动详情 |
| isPublic | int | 否 | 2 | 是否公开 1 公开 2 不公开 默认2 |

* 请求示例

```js
{
	"errCode": 0,
	"result": 0,
	"msg": "添加成功!",
	"time": 1529643310344
}
```

### 11.3 获取用户的备忘录列表（目前没有用到）

- 请求地址：auth/memo/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| start | long | 否 | 无 | 开始时间戳 |
| end | long | 否 | 无 | 结束时间戳 |

* 请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    time: 1529766575902, 
    data: [
        {
            id: 100, 
            userId: 4, 
            targetTime: 1529815765000, 
            detail: "不如去大理", 
            type: 1, 
            created: 1529650753000, 
            nickname: "大秦帝国", 
            avatar: "https://io.shanren.group/image/avatar.jpg"
        }, 
        {
            id: 101, 
            userId: 4, 
            targetTime: 1529815765000, 
            detail: "既然不快", 
            type: 1, 
            created: 1529650753000, 
            nickname: "大秦帝国", 
            avatar: "https://io.shanren.group/image/avatar.jpg"
        }
    ]
}
```


| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| :---: | :---: | :---: | :---: | :---: |
| id | int | 是 | 备忘录id |
| userId | int | 是 | 此条备忘录的创建者id |
| targetTime | long | 是 | 备忘时间 |
| detail | string | 是 | 备忘信息 |
| type | int | 是 | 1 活动 2 事件 |
| nickname | string | 是 | 创建者昵称 |
| avatar | string | 是 | 创建者头像 |



### 11.4 查看备忘录详情（目前没有用到）

- 请求地址：auth/memo/detail
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id | int | 是 | 无 | 备忘录id |
| type | int | 是 | 无 | 1 活动 2 事件 |

* 请求结果示例

```js
// 活动详情
{
    errCode: 0, 
    result: 0, 
    time: 1529925675079, 
    data: {
        id: 101, 
        userId: 1, 
        targetTime: 1529815765000, 
        address: "大理", 
        count: 1, 
        title: "去大理", 
        detail: "不如去大理", 
        users: "4", 
        created: 1529643309000, 
        updated: 1529643309000
    }
}
// 事件详情
{
    errCode: 0, 
    result: 0, 
    time: 1529926134983, 
    data: {
        id: 101, 
        userId: 4, 
        eventTime: 1529815765000, 
        targetTime: 1529815765000, 
        title: "", 
        detail: "既然不快", 
        isPublic: 1, 
        created: 1529650753000, 
        updated: 1529650753000
    }
}
```



### 11.5 删除一条备忘录（目前没有用到）

- 请求地址：auth/memo/del
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id | int | 是 | 无 | 备忘录id |
| type | int | 是 | 无 | 1 活动 2 事件 |


### 11.6 修改活动信息（目前没有用到）

- 请求地址：auth/memo/activity/modify
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id | int | 是 | 无 | 备忘录id |
| targetTime | long | 是 | 无 | 备忘时间（13位时间戳） |
| address | string | 是 | 无 | 地点 |
| count | int | 是 | 无 | 人数 |
| users | string | 是 | 无 | 邀请的好友的user id集合，多个用,隔开 |
| title | string | 是 | 无 | 标题 |
| detail | string | 是 | 无 | 活动详情 |

* 请求示例

```js
{
	"errCode": 0,
	"result": 0,
	"msg": "添加成功!",
	"time": 1529643310344
}
```



### 11.7 修改事件（目前没有用到）

- 请求地址：auth/memo/event/add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id | int | 是 | 无 | 备忘录id |
| targetTime | long | 是 | 无 | 备忘时间（13位时间戳） |
| eventTime | long | 是 | 无 | 事件时间 |
| detail | string | 是 | 无 | 活动详情 |
| isPublic | int | 否 | 2 | 是否公开 1 公开 2 不公开 默认2 |

* 请求示例

```js
{
	"errCode": 0,
	"result": 0,
	"msg": "添加成功!",
	"time": 1529643310344
}
```


### 11.8 查看好友通讯录（目前没有用到）

- 请求地址：auth/memo/list/friend
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| friendUserId | int | 是 | 无 | 好友用户id |
| start | long | 否 | 无 | 开始时间戳 |
| end | long | 否 | 无 | 结束时间戳 |

* 请求示例（和11.3一致）




### 11.9 添加备忘录事件

- 请求地址：auth/memo/affair/add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| targetTime | long | 是 | 无 | 备忘时间（13位时间戳） |
| detail | string | 是 | 无 | 活动详情 |
| users | string | 否 | 无 | 邀请的好友的user id集合，多个用,隔开 |
|isCirculation|byte|是|无|添加的事件是否循环 1-循环，2-不循环|

* 请求示例

```js
{
	"errCode": 0,
	"result": 0,
	"msg": "添加成功!",
	"time": 1529643310344
}
```


### 11.10 修改备忘录事件

- 请求地址：auth/memo/affair/modify_affair
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id | int | 是 | 无 | 备忘录id |
| targetTime | long | 是 | 无 | 备忘时间（13位时间戳） |
| detail | string | 是 | 无 | 活动详情 |
| users | string | 否 | 无 | 邀请的好友的user id集合，多个用,隔开 |


* 请求示例

```js
{
	"errCode": 0,
	"result": 0,
	"msg": "修改成功!",
	"time": 1529643310344
}
```


### 11.11 获取用户的备忘录列表

- 请求地址：auth/memo/affair/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| start | long | 否 | 无 | 开始时间戳 |
| end | long | 否 | 无 | 结束时间戳 |

* 请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    time: 1529766575902, 
    data: [
        {
      "id": 6,
      "userId": 6,
      "targetTime": 1573178951000,
      "detail": "过生日d",
      "type": null,
      "created": 1546919878000,
      "nickname": "林琴",
      "users": "1,15",
      "avatar": "https://liyuquan.cn/staticorder/comment/20181227/1545881538521936-600-600.jpg"
    },
     {
      "id": 7,
      "userId": 1,
      "targetTime": 1573178951000,
      "detail": "h很健康",
      "type": null,
      "created": 1546919878000,
      "nickname": "路遥",
      "users": null,
      "avatar": "https://liyuquan.cn/staticorder/comment/20181231/1546231284130051-600-600.jpg"
    }
    ]
}
```


| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| :---: | :---: | :---: | :---: | :---: |
| id | int | 是 | 备忘录id |
| userId | int | 是 | 此条备忘录的创建者id |
| targetTime | long | 是 | 备忘时间 |
| detail | string | 是 | 备忘信息 |
| type | int | 否 | 目前没用到 |
| nickname | string | 是 | 创建者昵称 |
| avatar | string | 是 | 创建者头像 |




### 11.12 删除一条备忘录

- 请求地址：auth/memo/affair/del
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id | int | 是 | 无 | 备忘录id |



* 请求示例

```js
{
	"errCode": 0,
	"result": 0,
	"msg": "删除成功!",
	"time": 1529643310344
}
```


### 11.13 查看好友备忘录

- 请求地址：/auth/memo/affair/list/friend
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| friendUserId | int | 是 | 无 | 好友用户id |
| start | long | 否 | 无 | 开始时间戳 |
| end | long | 否 | 无 | 结束时间戳 |

* 请求示例（和11.13一致）



## 12 通讯录=秀秀

### 12.1 发布秀秀

- 请求地址：auth/moment/publish
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| content | string | 否 | 无 | 文字内容 |
| open |byte|是|无|是否公开 1 公开 2好友可见|
| medias | string | 否 | 无 | 图片或视频json字符串 |
| showGift | int | 否 | 2 | 1 显示最近收到的礼物 2 不显示 |

* medias 结构

```js
{
	"type": 1, // 1 图片 2 视频
    "url":"pic.jpg"  // 图片或视频的相对地址
}
```

* 请求示例
```js
{
    errCode: 0, 
    result: 0, 
    msg: "发布成功!", 
    time: 1529983667667
}
```


### 12.2 赞|取消赞

- 请求地址：auth/moment/praise
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| momentId | int | 是 | 无 | 秀秀id |

* 请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    time: 1529982834960, 
    data: 2 // 1 赞 2 取消赞
}
```



### 12.3 评论

- 请求地址：auth/moment/comment
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| momentId | int | 是 | 无 | 秀秀id |
| content | string | 是 | 无 | 评论内容 |

* 请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    msg: "评论成功!", 
    time: 1529983667667
}
```




### 12.4 回复

- 请求地址：auth/moment/reply
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| commentId | int | 是 | 无 | 评论id |
| content | string | 是 | 无 | 回复内容 |

* 请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    msg: "回复成功!", 
    time: 1529983667667
}
```


### 12.5 秀秀列表）

- 请求地址：auth/moment/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| type | int | 是 | 1 | 1 全部 2 只获取好友的 |
| pageSize | int | 是 | 14 | 分页大小 |
| pageNum | int | 是 | 1 | 分页 |

```js
{
    errCode: 0, 
    result: 0, 
    time: 1530003562419, 
    data: [
        {
            momentId: 1, 
            isFriend: 1, // 1 我和这个人是好友关系 2 不是好友
            content: "既然不快乐又不喜欢这里",
            showGift: 1, // 1 显示 2 不显示
            medias: [ // 包含的图片或视频
                {
                    type: 1, // 1 图片 2 视频
                    url: "https://io.shanren.group/image/avatar.jpg"
                }
            ], 
            created: 1529988931000, 
            updated: 1529988931000, 
            selfUserId: 4, // 用户自己的id
            createUserId: 4, // 创建者用户id
            nickname: "大秦帝国", // 创建者昵称
            "type": 2,  //1 全部 2 只获取好友的 
            avatar: "https://io.shanren.group/image/avatar.jpg", 
            remark: "", // 我对创建者的备注名称
            relationship: "", // 我和创建者的关系
            praiseUsers: [ // 攒过的用户列表
                {
                    praiseUserId: 4, // 赞的用户id
                    momentId: 1, 
                    avatar: "https://io.shanren.group/image/avatar.jpg"
                }
            ], 
            comments: [ // 评论列表
                {
                    commentId: 4, // 评论id
                    content: "何时去大理", //评论内容
                    momentId: 1, 
                    type: 2, // 1评论 2回复
                    createUser: { // 评论创建者信息
                        userId: 4, 
                        nickname: "大秦帝国", 
                        remark: "", 
                        relationship: "", 
                        avatar: "https://io.shanren.group/image/avatar.jpg"
                    }, 
                    targetUser: { // 评论回复者信息
                        userId: 4, 
                        nickname: "大秦帝国", 
                        remark: "", 
                        relationship: "", 
                        avatar: "https://io.shanren.group/image/avatar.jpg"
                    }, 
                    created: 1529984133000
                }, 
                {
                    commentId: 3, 
                    content: "何时去大理评论", 
                    momentId: 1, 
                    type: 1, 
                    createUser: {
                        userId: 4, 
                        nickname: "大秦帝国", 
                        remark: "", 
                        relationship: "", 
                        avatar: "https://io.shanren.group/image/avatar.jpg"
                    }, 
                    targetUser: null, 
                    created: 1529983666000
                }
            ]
            gifts:[ // 最近30天收到的礼物
                   {
                    "title": "LIFELONG手镯", //礼物标题
                    "targetId": 16,//targetType=1时skuId,2时虚拟物品id，3时优惠券id 
                    "targetType": 1, // 礼物类型 1 物品 2 虚拟物品 3 优惠券
                     "cover":  "https://liyuquan.cn/static/item/181219/83bc3d85-0c2a-4 ee5-83b3-7e9c982f3856.jpg", //物品封面
                     "count": 2，  //礼物数量
                     "created": 1546574353000  
                     },
                  {
                    "title": "花朵",
                    "targetId": 3,
                    "targetType": 2,
                    "cover": "https://liyuquan.cn/static/item/181219/90716484-9e61-41e5-a9a7-56cfc7dfafc2.png",
                    "count": 2，
                     "created": 1546574353000
                 },
                ]
        }
    ]
}
```

|   参数名称   | 参数类型 |  参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| momentId | int | 是 | 秀秀id |
| createUserId | int | 是 | 创建用户id |
| content | string | 是 | 文字内容 |
| selfUserId | int | 是 | 我自己id |
| avatar | string  | 是 | 创建者头像 |
| nickname | string | 是 | 创建者昵称 |
| remark | string | 是 | 我对创建者的备注 |
| relationship | string | 是 | 我与创建用户的关系 |
| medias | 媒体内容
| | type | int | 媒体类型 1 图片 2 视频 |
| | url | string | 视频或图片地址 |
| praiseUsers | 赞过的用户列表 |
| | pariseUserId | int | 赞的用户id |
| | avatar | string | 头像 |
| comments | 评论列表 |
| | commentId | int | 评论id |
| | conent | string | 评论内容 |
| | type | int | 1 评论 2 回复 |
| | createUser | obj | 创建这条评论的用户的信息 |
| | targetUser | obj | 回复对象用户的信息 |


### 12.6 秀秀详情

- 请求地址：auth/moment/detail
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| momentId | int | 是 | 无 | 秀秀id |

* 请求结果和12.5一致


### 12.7 查看某个用户的秀秀列表

- 请求地址：auth/moment/list/self
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| targetUserId | int | 否 | 无 | 查看用户的id,默认不传查看自己的 |

* 请求结果和12.5一致


### 12.8 删除评论

- 请求地址：auth/moment/comment/del
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| commentId | int | 否 | 无 | 评论id |



### 12.9 删除秀秀

- 请求地址：auth/moment/del
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| momentId | int | 否 | 无 | 秀秀id |




### 12.10 用户未查看的评论和点赞

- 请求地址：auth/moment/count
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |


* 请求结果示例

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1547199348380,
  "data": {
    "commentCount": 0,  //未查看的评论数量
    "pariseCount": 0    //未查看的点赞数量
  }
}
```





## 13. 商品提货
### 13.1 商品提货订单
- 请求地址：auth/receive/item/order
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin



|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| bpId | int | 是 | 无 | 背包商品id |
| shippingId | int | 是 | 无 | 收货地址id |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "订单创建成功", 
    "time": 1530242784287
}
```

### 13.2 提货订单列表
- 请求地址：auth/receive/item/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin



|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| status | int | 是 | 无 | 订单状态，0-全部，1-未完成，2-已完成 |
| pageNum | int | 否 | 1 | 分页 |
| pageSize | int | 否 | 14 | 分页大小 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1530253021156, 
    "data": [
        {
            "title": "魏文侯",  
            "description": "", 
            "orderNo": 418062911100, 
            "cover": "https://io.shanren.group/image/cover.jpg", 
            "price": 1000, 
            "status": 3,  //订单状态，1-待发货；2-已发货；3-已收货,待评价，4-已评价,5-取消，6-删除
            "created": 1530242782000, 
            "updated": 1530242782000,
            "itemId": 54,
            "skuId":2
        }
    ]
}
```

### 13.3 提货订单详情
- 请求地址：auth/receive/item/detail
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin



|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| orderNo | int | 是 | 无 | 订单号 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1530451554628, 
    "data": {
        "id": 1, 
        "userId": 1, 
        "itemId": 1, 
        "bpItemId": 2, 
        "skuId": 2, 
        "orderNo": 418062821101, 
        "title": "魏文侯", 
        "description": "", 
        "cover": "https://io.shanren.group/image/cover.jpg", 
        "price": 1000, 
        "quantity": 1, 
        "receiveInfo": {
            "id": 1, 
            "userId": 1, 
            "consigneeName": "小明", 
            "address": "云南省昆明市五华区", 
            "phone": "13965869852", 
            "addressDetail": "云南省昆明市五华区一二一大街126号1单元001", 
            "adcode": 530102, 
            "status": 1, 
            "created": 1528255980000, 
            "updated": 1528255983000
        }, 
        "logisticsInfo": {
            "expressCompany": "顺丰速运", 
            "com": "shunfeng", 
            "expressNo": "456487767225"
        }, 
        "logisticsTrace": [
            {
                "time": "2018-06-13 10:47:00", 
                "ftime": "2018-06-13 10:47:00", 
                "context": "[昆明市]已签收(放单车棚 ),感谢使用顺丰,期待再次为您服务", 
                "location": "昆明市"
            }, 
            {
                "time": "2018-06-13 08:19:00", 
                "ftime": "2018-06-13 08:19:00", 
                "context": "[昆明市]快件交给付云飞，正在派送途中（联系电话：18082717410）", 
                "location": "昆明市"
            }, 
            {
                "time": "2018-06-13 08:15:00", 
                "ftime": "2018-06-13 08:15:00", 
                "context": "[昆明市]正在派送途中,请您准备签收(派件人:设定用户,电话:12345678911)", 
                "location": "昆明市"
            }, 
            {
                "time": "2018-06-13 08:08:00", 
                "ftime": "2018-06-13 08:08:00", 
                "context": "[昆明市]快件到达 【昆明正大紫都营业点】", 
                "location": "昆明市"
            }, 
            {
                "time": "2018-06-13 06:56:00", 
                "ftime": "2018-06-13 06:56:00", 
                "context": "[昆明市]快件已发车", 
                "location": "昆明市"
            }, 
            {
                "time": "2018-06-12 22:43:00", 
                "ftime": "2018-06-12 22:43:00", 
                "context": "[昆明市]快件在【昆明王家营集散中心】已装车,准备发往 【昆明正大紫都营业点】", 
                "location": "昆明市"
            }, 
            {
                "time": "2018-06-12 21:16:00", 
                "ftime": "2018-06-12 21:16:00", 
                "context": "[昆明市]快件到达 【昆明王家营集散中心】", 
                "location": "昆明市"
            }, 
            {
                "time": "2018-06-11 05:46:00", 
                "ftime": "2018-06-11 05:46:00", 
                "context": "[杭州市]快件已发车", 
                "location": "杭州市"
            }, 
            {
                "time": "2018-06-11 01:07:00", 
                "ftime": "2018-06-11 01:07:00", 
                "context": "[杭州市]快件在【杭州上城集散中心】已装车,准备发往下一站", 
                "location": "杭州市"
            }, 
            {
                "time": "2018-06-10 22:55:00", 
                "ftime": "2018-06-10 22:55:00", 
                "context": "[杭州市]快件到达 【杭州上城集散中心】", 
                "location": "杭州市"
            }, 
            {
                "time": "2018-06-10 19:38:00", 
                "ftime": "2018-06-10 19:38:00", 
                "context": "[宁波市]快件已发车", 
                "location": "宁波市"
            }, 
            {
                "time": "2018-06-10 17:59:00", 
                "ftime": "2018-06-10 17:59:00", 
                "context": "[宁波市]顺丰速运 已收取快件", 
                "location": "宁波市"
            }, 
            {
                "time": "2018-06-10 17:59:00", 
                "ftime": "2018-06-10 17:59:00", 
                "context": "快件在【宁波市鄞州区机场营业点】已装车,准备发往下一站", 
                "location": "宁波市"
            }
        ], 
        "status": 4, 
        "created": 1530443381000, 
        "updated": 1530443381000
    }
}

```

### 13.4 提货订单状态处理,确认收货
- 请求地址：auth/receive/item/confirm
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| orderNo | int | 是 | 无 | 订单号 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "确认收货成功", 
    "time": 1530452052793
}
```

### 13.5 提货订单状态处理,订单评论
- 请求地址：auth/receive/item/comment
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| orderNo | int | 是 | 无 | 订单号 |
| star | int | 是 | 无 | 评价星星 |
| content | String | 是 | 无 | 评论文字 |
| pictures | String | 否 | 无 | 评论照片 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "评论成功", 
    "time": 1530443384551
}
```


### 13.6 商品提货前判断商品是否下架
- 请求地址：auth/receive/item/check
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| bpId | long | 是 | 无 | 背包id |


请求结果示例：

```json
{
  "errCode": 1,
  "result": 0,
  "msg": "该商品规格已经下架，请选择其他处理方法或者联系客服！",
  "time": 1548824443327
}
```

### 13.7 小程序商品提货
- 请求地址：auth/receive/item/order_wx
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| bpId | long | 是 | 无 | 背包商品id |
| address |  string  |    是    |   无   | 所在地区(需要拼接) |
| addressDetail |  string  |    是    |   无   | 收货人详细地址 |
| consigneeName |  string  |    是    |   无   | 收货人 |
| adcode |  Int  |    是    |   无   | 所在地区id |
| phone |  string  |    是    |   无   | 联系电话 |
|code |int|是|无|验证码|

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "订单创建成功", 
    "time": 1530242784287
}
```

### 13.8 小程序商品折现
- 请求地址：auth/v1/withdraw/add_wx
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| bpId | long | 是 | 无 | 背包商品id |
|type|int|是|无|1 支付宝，2 银行卡|
| depositBank  |  string  |    是    |   无   | 开户行名称（type=2时必填 |
|  cardHolder  |  string  |    是    |   无   | 持卡人姓名（type=1时收款人姓名） |
|    cardNo    |  string  |    是    |   无   |  银行卡号（type=1时支付宝账号）  |
|    bankId    |   Int    |    是    |   无   |   银行id（type=2时必填）   |
| phone |  string  |    否    |   无   | 联系电话 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "成功", 
    "time": 1530242784287
}
```

### 13.9 小程序商品折现记录
- 请求地址：auth/v1/withdraw/wxRecords
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1552571451761,
  "data": [
    {
      "id": 1,
      "bankId": 8,
      "userId": 6,
      "bpId": 7719031310110,
      "cardNo": "62480033440006666",
      "type": 2,
      "depositBank": "高新支行", // 支行
      "cardHolder": "wo", // 持卡人
      "phone": "",
      "created": null, // 创建事件
      "updated": null,
      "status": 1, // 1 折现中 2 成功 3 失败
      "amount": 420.75, // 商品折现后的金额
      "price": 495.00, // 商品价格
      "cover": "/item/190130/2062e68e-bdf8-4294-9299-b1bffcab7b36.jpg",
      "title": "【官方正品】纪梵希绒雾哑光粉饼 遮瑕控油 持久轻薄 定妆粉  标准款"
    }
  ]
}

```


## 14 背包

### 14.1 背包列表

- 请求地址：auth/v1/bp/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| type | int | 是 | 无 | 类型 1 物品 2 虚拟物品 3 优惠券 |
| pageNum | int | 是 | 无 | 分页 |
| pageSize | int | 是 | 无 | 分页大小 |

* 请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    time: 1530691273381, 
    data: [
        {
            id:7719021911112,        // 背包id  （优惠券时券码）        
            userId: 1, //用户id
            targetId: 2,  //目标物品id，type=1商品skuId，type=2虚拟物品id，type=3优惠券id
            price: 1000,   //价格
            title: "魏文侯",   //标题
            cover: "https://io.shanren.group/image/cover.jpg",  
            description: "东周末年", //描述
            brand: "公牛", //品牌
            type: 1, //1商品 ，2-虚拟物品 3，优惠券
            quantity: 0, 
             "pickRemainTime": 2591891873, //提货剩余时间（从购买到30天的剩余毫秒数）
            "created": 1550547983000,
            "buyTime": 1550547983000  //购买时间
        }, 
        {
            "id": 7719021911113,
            "userId": 6,
             "targetId": 199,
            "price": 300.00,
            "title": "NARS遮瑕膏 遮盖痘印 甜蜜Honey 甜奶油白Chantilly  标准款",
             "cover": "https://liyuquan.cn/static/item/190212/21b8753f-049a-43fb-bcfa-a1bea105d7ae.jpg",
             "description": "产品参数：\n产品名称：Nars/娜斯 柔哑净瑕遮瑕膏\n品牌: Nars/娜斯遮瑕笔/遮瑕膏\n单品: 柔哑净瑕遮瑕膏\n产地: 美国\n颜色分类: Honey Chantilly Cannelle Custard Vanilla Crème Brulee\n保质期: 36个月\n功效: 遮瑕\n规格类型: 正常规格\n是否为特殊用途化妆品: 否\n限期使用日期范围: 2021-06-01至2021-06-01\n化妆品保质期: 36个月",
            "brand": "Nars官方旗舰店",
             "type": 1,
             "quantity": 1,
            "pickRemainTime": 2591891873,
            "created": 1550547983000,
            "buyTime": 1550547983000
       },
      {
             "id": 7719021911116,
             "userId": 6,
             "targetId": 200,
             "price": 380.00,
             "title": "NARS阴影修容粉 立体修颜 古铜珠光拉古纳Laguna  标准款",
            "cover": "https://liyuquan.cn/static/item/190212/5070d53d-e0dc-47b3-a16d-7e0d171b759a.jpg",
            "description": "产品参数：\n产品名称：Nars/娜斯 阴影修容粉\n是否为特殊用途化妆品: 否\n功效: 立体\n保质期: 3年\n规格类型: 正常规格\n产地: 美国\n品牌: Nars/娜斯修颜/高光/阴影粉\n单品: 阴影修容粉\n净含量: 8g\n颜色分类: Laguna",
            "brand": "Nars官方旗舰店",
            "type": 1,
            "quantity": 1,
            "pickRemainTime": 2591989873,
             "created": 1550548080000,
             "buyTime": 1550548081000
       }
    ]
}
```

### 14.2 背包搜索

- 请求地址：auth/v1/bp/search
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| key | String | 是 | 无 | 搜索关键字 |
| pageNum | int | 是 | 无 | 分页 |
| pageSize | int | 是 | 无 | 分页大小 |

* 请求结果和14.1一样

### 14.3 背包物品折现（14.9获取折现比例）

- 请求地址：auth/v1/discount/add_record
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| bpId | long | 是 | 无 | 背包id |

* 请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "折现成功", 
    "time": 1531365179080
}
```

### 14.4 好友背包物品列表

- 请求地址：auth/friend/bp/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| friendUserId | int | 是 | 无 | 好友用户id |
| type | int | 是 | 无 | 1 物品 2 虚拟物品 3 优惠券 |
| pageNum | int | 是 | 无 | 分页 |
| pageSize | int | 是 | 无 | 分页大小 |

* 请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531378367533, 
    "data": [
        {
            "id": 7718071116139, 
            "userId": 4, 
            "targetId": 2, //目标物品Id
            "price": 0.01, 
            "title": "2元现金券", 
            "cover": "https://io.shanren.group/static/upload/image//item/180711/f5b7f1d6-3c18-457b-b920-b1a941337862.png", 
            "description": "{
 \"type\": \"优惠券\",
 \"discunt\":\"1元\",
 \"decription\":\"仅限购买三只松鼠\"
}", 
            "brand": "七牛", //品牌
            "type": 3, 
            "quantity": 1, 
            "created": 1531297967000
        }, 
        {
            "id": 7718070611101, 
            "userId": 4, 
            "targetId": 2, 
            "price": 100, 
            "title": "2元现金券", 
            "cover": "https://io.shanren.group/static/upload/image//item/180711/f5b7f1d6-3c18-457b-b920-b1a941337862.png", 
            "description": "{
 \"type\": \"优惠券\",
 \"discunt\":\"1元\",
 \"decription\":\"仅限购买三只松鼠\"
}", 
            "brand": "七牛", 
            "type": 3, 
            "quantity": 1, 
            "created": 1530847417000
        }
    ]
}
```

### 14.5 好友背包物品详情

- 请求地址：auth/friend/bp/detail
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| friendUserId | int | 是 | 无 | 好友用户id |
| bpId | int | 是 | 无 | 背包id |

* 请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531377954178, 
    "data": [
        {
            "id": 7718071115126, 
            "userId": 4, 
            "targetId": 1, //目标物品id
            "price": 0.01, 
            "title": "小苹果", 
            "cover": "https://io.shanren.group/static/upload/image/http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132", 
            "description": "象征和平的小苹果", 
            "brand": "不知名", 
            "type": 2, 
            "quantity": 1, 
            "created": 1531294845000
        }
    ]
}
```

### 14.6 好友背包物品添加到索要记录

- 请求地址：auth/friend/bp/add_for_record
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| friendUserId | int | 是 | 无 | 好友用户id |
| bpId | int | 是 | 无 | 背包id |

* 请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "向好友发起索要背包物品消息成功", 
    "time": 1531447797626
}
```

### 14.7 索要记录列表

- 请求地址：auth/friend/bp/record_list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
|type|int|是|无|1-用户向好友索要商品 ，2-好友向用户索要商品|
| pageNum | int | 是 | 无 | 分页 |
| pageSize | int | 是 | 无 | 分页大小 |

* 请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531448171000, 
    "data": [
        {
            "id": 1, 
            "userId": 1, 
            "bpId": 1, 
            "friendUserId": 4, 
            "status": 1, 
            "operation": 0, 
            "type": 1, 
            "cover": "https://io.shanren.group/static/upload/image/cover.jpg", 
            "title": "魏文侯", 
            "price": 100, 
            "brand":"施华洛世奇"
            "avatar": "https://io.shanren.group/static/upload/image/avatar.jpg", 
            "nickname": "大秦", 
            "created": 1531387784000
        }, 
        {
            "id": 3, 
            "userId": 1, 
            "bpId": 7718071116140, 
            "friendUserId": 4, 
            "status": 1, 
            "operation": 0, 
            "type": 1, 
            "cover": "https://io.shanren.group/static/upload/image/cover.jpg", 
            "title": "秦孝公", 
            "price": 0.01, 
            "brand":"施华洛世奇"
            "avatar": "https://io.shanren.group/static/upload/image/avatar.jpg", 
            "nickname": "大秦", 
            "created": 1531447827000
        }
    ]
}
```

|   参数名称   | 参数类型 | 是否必传 | 参数说明 |
| :----------: | :------: | :------: | :------: |
| bpId | int | 是 | 背包id |
| friendUserId | int | 是 | 好友用户id |
| status | int | 是 | 索要状态，1-索要中，2-索要成功，3-索要失败 |
| operation | int | 是 | 0-默认无操作，1-同意好友索要，2-拒绝好友索要 |
| type | int | 是 | 1-用户向好友索要商品 ，2-好友向用户索要商品 |
| cover | string | 是 | 商品封面 |
| title | string | 是 | 商品标题 |
| price | int | 是 | 商品价格 |
|brand|string|是|商品品牌|
| avatar | string | 是 | 好友头像 |
| nickname | string | 是 | 好友昵称 |

### 14.8 同意或者拒绝好友索要背包物品

- 请求地址：auth/friend/bp/operation
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| forRecordId | int | 是 | 无 | 索要记录id |
| operation | byte | 是 | 无 | 0-默认无操作，1-同意好友索要，2-拒绝好友索要 |

* 请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "已同意好友索要物品", 
    "time": 1531466941300
}
```

### 14.9 获取折现比例

- 请求地址：auth/v1/discount/preinfo
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

- 请求结果示例

```js
{
    errCode:0,
    data: {
        ratio: 0.15, // 折现比例
        desc: "折现说明"
    }
}
```



### 14.10 删除索要记录

- 请求地址：auth/friend/bp/delete_record
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
|recordId|string|是|无|索要记录id（删除多个时用逗号隔开）|

- 请求结果示例

```js
{
  "errCode": 1,
  "result": 0,
  "msg": "删除索要记录成功",
  "time": 1545988151459
}
```



## 15 背包-礼物赠送

### 15.1 app礼物赠送（原来）

- 请求地址：auth/v1/gift/sendForApp
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| friendUserId | int | 是 | 无 | 赠送的好友id |
| bpId | int | 是 | 无 | 赠送的物品在背包里的id |
| subBpIds | string | 否 | 无 | 赠送的附属物品的id集合，多个用，隔开 |
| greeting | string | 是 | 无 | 祝福语 |
| event | string | 是 | 无 | 事件名称 |
| type | int | 是 | 无 | 1 立即赠送 2 按时间赠送|
| targetTime | long | 是 | 无 | 赠送时间的时间戳 |
| recordDetailId | int | 否 | 无 | 赠送记录详情id |
|de|string|是|无|赠送密码|
|s2|string|是|无|客户端随机数|

### 15.2 礼物赠送答谢

- 请求地址：auth/v1/gift/acknowledge
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| recordDetailId | int | 是 | 无 | 记录详情id |
| reply | string | 是 | 答谢的文字内容 |

### 15.3 礼物赠送选择事件列表

- 请求地址：auth/event/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

请求结果示例：
```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1532671934449, 
    "data": [
        {
            "id": 1, 
            "userId": 0, 
            "eventName": "生日送礼",  //事件名称
            "created": 1528791212000, 
            "updated": 1528791215000
        }, 
        {
            "id": 2, 
            "userId": 0, 
            "eventName": "母亲节送礼", 
            "created": 1528791381000, 
            "updated": 1528791384000
        }
    ]
}
```

### 15.4 礼物赠送添加事件

- 请求地址：auth/event/add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| eventName |  string  |    是    |   无   | 事件名称 |

请求结果示例：
```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "事件添加成功", 
    "time": 1532672461475
}
```

### 15.5 微信礼物赠送（分享之前需请求该接口）

- 请求地址：auth/v1/gift/sendForWx
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| bpId | int | 是 | 无 | 赠送的物品在背包里的id |
| subBpIds | string | 否 | 无 | 赠送的附属物品的id集合，多个用，隔开 |
| greeting | string | 是 | 无 | 祝福语 |
| event | string | 否 | 小程序赠送 | 事件名称 |
| type | int | 是 | 无 | 3 直接赠送 4 随机赠送（app 只有直接赠送） |
| p | float | 否 | 无 | type=4时必传 中奖率 0-1之间的小数 |


* 请求结果示例

```js
{
    errCode: 0.
    data: {
        giftRecordId: 101 // 礼物赠送记录id，分享此id
    }
}
```

### 15.6 微信领取好友分享的礼物

- 请求地址：auth/v1/gift/getGift
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| giftRecordId | int | 是 | 无 | 礼物记录id |

× 请求结果示例

```js
// type=3 直接送礼时放回结果
{
    errCode: 0, // 0 领取成功 20003 礼物不存在或已过期 20004 礼物已被领取或已过期 20005 礼物不存在
    data: {
        type: 3,
        giftInfo:  {
            recordDetailId: 2,  // 礼物赠送记录详情id，可用于答谢
            userId: 4, 
            greetting: "一厢情愿的不舍", // 祝福语
            giftItems: [ // 赠送的礼物信息
                {
                    bpId: 2,  // 背包id
                    targetId: 2,  
                    targetType: 3, // 1 物品 2 虚拟物品 3 优惠券 
                    giftType: 1,  
                    price: 100, // 物品价格
                    title: "1元优惠券",  // 物品名称
                    cover: "https://io.shanren.group/image/cover.jpg", 
                    description: "{ // 物品描述
 \"type\": \"优惠券\",
 \"discunt\":\"1元\",
 \"decription\":\"仅限购买三只松鼠\"
}", 
                    brand: "小牛" // 物品品牌
                }
            ], 
            // 赠送者信息
            sendUserId: 1, // 赠送者用户id 
            isReply: 2, // 1 已答谢 2 未答谢
    }
}

// type=4 随机送礼返回
{
    errCode: 0, // 0 领取成功 20001 运气太差啦，没有抢到礼品! 20002 手速太慢啦，礼品都没有了!
    data: {
        type: 3,
        giftInfo:  {
            reNum: 10, // 剩余礼物数量
            recordDetailId: 2,  // 礼物赠送记录详情id，可用于答谢
            userId: 4, 
            greetting: "一厢情愿的不舍", // 祝福语
            giftItems: [ // errCode=0时返回领取到的礼物信息，errCode=20001时返回剩余礼物信息
                {
                    bpId: 2,  // 背包id
                    targetId: 2,  
                    targetType: 3, // 1 物品 2 虚拟物品 3 优惠券 
                    giftType: 1,  
                    price: 100, // 物品价格
                    title: "1元优惠券",  // 物品名称
                    cover: "https://io.shanren.group/image/cover.jpg", 
                    description: "{ // 物品描述
 \"type\": \"优惠券\",
 \"discunt\":\"1元\",
 \"decription\":\"仅限购买三只松鼠\"
}", 
                    brand: "小牛" // 物品品牌
                }
            ], 
            // 赠送者信息
            sendUserId: 1, // 赠送者用户id 
            isReply: 2, // 1 已答谢 2 未答谢
    }
}
```

### 15.7 赠送记录/收礼记录

- 请求地址：auth/v1/gift_send/send_list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| flag |  int  |    是    |   无   | 1 赠送记录 2 收礼记录 |
|pageNum|int|是|无|分页|
|pageSize|int|是|无|分页大小|

请求结果示例：（）
```json

{
  "errCode": 0,
  "result": 0,
  "time": 1546825810262,
  "data": [
    {
      "id": 50,   //赠送记录id
      "userId": 6,  //赠送者id
      "greetting": "快来",  //祝福语
      "type": 1,  //1 立即赠送 2 按时间赠送 3 小程序选择好友赠送需要领取 4 小程序随机赠送需要领取
      "event": "时间",  //赠送事件
      "targetTime": 1546596929000,  //按时赠送的赠送事件
      "status": 3,  //1 未领取 2 已领取部分 3 已领取全部 （flag=1时的状态，所有状态都显示已赠送）
      "updated": 1546596929000,  //更新时间
      "nickname": "林琴",  //赠送者用户昵称
      "avatar": "order/comment/20181227/1545881538521936-600-600.jpg",  //赠送者用户头像
      "flag": 1,  //1 赠送记录  2 收礼记录
      "detail": [   //收礼详情
        {
          "id": 50,   //记录详情id
          "giftRecordId": 50, //赠送记录id
          "userId": 14,  //接收者用户id
          "amount": null,
          "content": [  //礼物详情
            { 
              "bpId": 7719010417127,  //背包id
              "targetId": 17,  //目标物品id，type=1商品skuId，type=2虚拟物品id，type=3优惠券id
              "targetType": 1,  // 1 物品 2 虚拟物品 3 优惠券 
              "giftType": 1,
              "price": 0.02,  //礼物价格
              "title": "LOVELY浪漫爱心项链锁骨链女首饰",  //物品标题
              "cover": "https://liyuquan.cn/static/item/181219/5e6b190c-8add-4f1d-987f-79c432fc14e1.jpg",  //物品封面图
              "description": "LOVELY浪漫爱心项链锁骨链女首饰浪漫清新表达爱意",  //物品描述
              "brand": "施华洛世奇" //物品品牌
            },
            {
              "bpId": 7719010411111,
              "targetId": 1,
              "targetType": 2,
              "giftType": 2,
              "price": 111.00,
              "title": "草莓",
              "cover": "https://liyuquan.cn/static/item/181219/871493ad-2826-4443-a060-e29e61dfc1ba.png",
              "description": "小草莓和蝴蝶",
              "brand": "圣罗兰"
            }
          ],
          "isReply": 2,  //1答谢 2未答谢
          "reply": "",  //答谢内容
          "status": 3,   // 1 未领取 2 已领取部分 3 已领取全部 （flag=2时的状态，1为未接收，2，3为已接收）
          "updated": 1546596929000, //更新时间
          "created": 1546596929000, //创建时间
          "friendNickname": "年锐", //收礼者昵称
          "friendAvatar":  "order/comment/20190104/1546595662441386-366-366.jpg" //收礼者头像
        }
      ]
    }
  ]
}

```


### 15.8 取消礼物赠送

- 请求地址：auth/v1/gift_send/cancel_send
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
|recordId|int|是|无|礼物赠送记录id|

* 请求结果示例

```js
{
  "errCode": 0,
  "result": 0,
  "msg": "撤回礼物赠送成功",
  "time": 1546843101963
}
```

### 15.9 删除赠送记录

- 请求地址：auth/v1/gift_send/delete_send
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
|recordId|string|是|无|礼物赠送记录id,多个时用逗号隔开|

* 请求结果示例

```js
{
  "errCode": 1,
  "result": 0,
  "msg": "删除赠送记录成功",
  "time": 1546843228867
}

```


### 15.10 删除收礼记录

- 请求地址：auth/v1/gift_send/delete_receive
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
|recordDetailId|string|是|无|礼物记录详情id，多个时用逗号隔开|

* 请求结果示例

```js
{
  "errCode": 1,
  "result": 0,
  "msg": "删除收礼记录成功",
  "time": 1546843228867
}

```


### 15.11 app礼物赠送（新加）

- 请求地址：auth/v1/gift/sendForAppV2
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen


|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| friendUserIds | String | 是 | 无 | 赠送的好友id,多个好友时用逗号隔开|
| bpId | int | 是 | 无 | 赠送的物品在背包里的id |
| greeting | string | 是 | 无 | 祝福语 |
| event | string | 否 | 日常关怀 | 事件名称 |
| type | int | 是 | 无 | 1 立即赠送 2 按时间赠送|
| targetTime | long | 否 | 无 | 赠送时间的时间戳（按时间赠送时传） |
| recordDetailId | int | 否 | 无 | 赠送记录详情id---回礼 |



## 16 消息

### 16.1 消息首页

- 请求地址：auth/v1/message/home
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

```js
{
    errCode: 0, 
    result: 0, 
    time: 1530850998639, 
    data: [
        {
            messageType: 1, 
            avatar: "", 
            title: "礼物通知", 
            summary: "什么送您了魏文侯", 
            unread: 1, // 未读消息数量
            created: 1530846904000
        }, 
        {
            messageType: 2, 
            avatar: "", 
            title: "系统通知", 
            summary: "", 
            unread: 0, 
            created: 1530850998276
        }, 
        {
            messageType: 3, 
            avatar: "", 
            title: "寄售台通知", 
            summary: "您交易的物品被什么用户购买，快去看看吧", 
            unread: 1, 
            created: 1530697205000
        }, 
        {
            messageType: 4, 
            avatar: "", 
            title: "礼物交换通知", 
            summary: "", 
            unread: 0, 
            created: 1530850998570
        }
    ]
}
```


### 16.2 消息列表

- 请求地址：auth/v1/message/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| messageType | int | 是 | 无 | 1 礼物通知 2 系统消息 3 寄售台通知 4 礼物交换通知 |
| pageNum | int | 否 | 1 | 分页 |
| pageSzie | int | 否 | 14 | 分页大小 |

* 礼物通知消息请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    time: 1530861017872, 
    data: [
        {
            summary: "计划发送您了1元优惠券", 
            messageType: "1", 
            recordDetailId: 2,  // 礼物赠送记录详情id，可用于答谢
            messageId: 105, // 消息id 
            userId: 4, 
            isRead: 2,  // 是否已读 1 已读 2 未读
            greetting: "一厢情愿的不舍", // 祝福语
            giftItems: [ // 赠送的礼物信息
                {
                    bpId: 2,  // 背包id
                    targetId: 2,  //目标物品id，type=1商品skuId，type=2虚拟物品id，type=3优惠券id
                    targetType: 3, // 1 物品 2 虚拟物品 3 优惠券 
                    giftType: 1,  
                    price: 100, // 物品价格
                    title: "1元优惠券",  // 物品名称
                    cover: "https://io.shanren.group/image/cover.jpg", 
                    description: "{ // 物品描述
 \"type\": \"优惠券\",
 \"discunt\":\"1元\",
 \"decription\":\"仅限购买三只松鼠\"
}", 
                    brand: "小牛" // 物品品牌
                }
            ], 
            // 赠送者信息
            sendUserId: 1, // 赠送者用户id 
            avatar: "https://io.shanren.group/image/avatar.jpg", 
            nickname: "帝王妃", 
            remark: "爱妃", 
            friendId: 1, // 好友id 为null代表不是好友
            relationShip: "", 
            reply: "",
			isReply: 0, // isReplay & 2 = 2 以答谢 isReplay & 4 = 4 已回礼
            created: 1530847416000
            targetTime: 1543273052000, 
        }
    ]
}
```

* 系统消息通知请求结果示例

```js
{
    "errCode": 0, 
    "result": 0, 
    "time": 1543544977797, 
    "data": [
        {
            "id": 14,   //消息id
            "summary": "您的折现已成功到达账户，请前往余额查看",  //简介
            "title": "系统通知",  //标题
            "content": "您好，你的【官方正品】纪梵希口红禁忌之吻霓虹唇膏 豆沙色保湿滋润 N11物品折现金额170.000元，原价格200.00元，您的折现已成功到达账户，请前往余额查看",  //消息通知
             "targetId": 12, 
            "targetType": 24,  //目标类型11 礼物实物 12 礼物虚拟物品 21折现 22 推送 23 提现 24 答谢
            "messageType": "2",  // 消息类型2 系统消息
            "created": 1543544601000,  //创建时间
            "userId": 1,  //用户id
            "isRead": 2 //是否已读 1 已读 2 未读
            "avatar": "https://io.shanren.group/image/cover.jpg" //好友头像
            "friend_user_id": 3  //好友id
        }
    ]
}
```


* 寄售台通知消息请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    time: 1530864883428, 
    data: [
        {
            messageId: 102, 
            targetId: 25, 
            summary: "您交易的物品被什么用户购买，快去看看吧", 
            messageType: 3, 
            price: 100, 
            title: "魏文侯", 
            description: "东周末年", 
            cover: "https://io.shanren.group/image/cover.jpg", 
            created: 1530697206000
        }, 
        {
            messageId: 101, 
            targetId: 19, 
            summary: "您交易的物品被null用户购买，快去看看吧", 
            messageType: 3, 
            price: 100, 
            title: "魏文侯", 
            description: "东周末年", 
            cover: "https://io.shanren.group/image/cover.jpg", 
            created: 1530693059000
        }
    ]
}
```


### 16.3 看过的消息改为为已读

- 请求地址：auth/v1/message/read
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
|ids|String|是|无|消息id，多个时用逗号隔开|

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1548930230811
}

```


## 17 首页接口

### 17.1 首页备忘录（需要登录）

- 请求地址：auth/memo/affair/home
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

```js
{
    errCode: 0, 
    result: 0, 
    time: 1530869222094, 
    data: [
        {
            id: 100, 
            userId: 4,  // 备忘录创建者id
            targetTime: 1531111765000,  // 设置的提醒时间
            detail: "忘记去大理",  // 提醒内容
            type: 2,  // 1 活动 2 事件
            created: 1529643309000, 
            nickname: "大秦", // 创建者昵称
            avatar: "https://io.shanren.group/image/avatar.jpg", // 创建者头像
            days: 2.8072 // 距离targetTime的剩余时间（天）
        }, 
        {
            id: 102, 
            userId: 4, 
            targetTime: 1531457365000, 
            detail: "又不喜欢这里", 
            type: 1, 
            created: 1529650753000, 
            nickname: "大秦", 
            avatar: "https://io.shanren.group/image/avatar.jpg", 
            days: 6.8072
        }
    ]
}
```

### 17.2 首页商品（首页Banner）

- 请求地址：noauth/v1/home/Banner
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin
- 无参数

请求结果示例：

```js
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531108167151, 
    "data": [
        {
            "id": 1, 
            "targetid": "1", 
            "cover": "https://io.shanren.group/image/cover.jpg", 
            "requesturl": "", 
            "title": "", 
            "type": 1, 
            "status": 1, 
            "createtime": 1530864451000, 
            "updatetime": 1530864456000, 
            "richtext": ""
        }, 
        {
            "id": 2, 
            "targetid": "1", 
            "cover": "https://io.shanren.group/image/cover.jpg", 
            "requesturl": "", 
            "title": "", 
            "type": 2, 
            "status": 1, 
            "createtime": 1530864480000, 
            "updatetime": 1530864483000, 
            "richtext": ""
        }
    ]
}
```
|   参数名称   | 参数类型 | 是否必传 | 参数说明 |
| :----------: | :------: | :------: | :------: |
| targetId |  String  |    是    |  跳转的目标id |
| cover |  String  |    是    |  封面图片 |
| requesturl |  String  |    是    |  跳转url |
| title |  String  |    是    |  图片标题 |
| type |  int  |    是    | banner类型,  1 跳转到商品详情 2 跳转到好物主题列表 3-跳转url,4-纯展示 |
| status |  int  |    是    |  1 正常 2 禁用 |
| richtext |  text  |    是    |  富文本 |


### 17.3 首页寄售台新上架商品

- 请求地址：noauth/v1/home/con_item
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin
- 无参数

请求结果示例：

```js
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531109046841, 
    "data": [
        {
            "consignmentId": 6, 
            "bpId": 1, 
            "targetId": 2, 
            "price": 200, 
            "type": 1, 
            "status": 1, 
            "cover": "https://io.shanren.group/image/cover.jpg", 
            "title": "魏文侯", 
            "updated": 1531103266000, 
            "created": 1531103266000
        }, 
        {
            "consignmentId": 4, 
            "bpId": 3, 
            "targetId": 1, 
            "price": 888, 
            "type": 1, 
            "status": 1, 
            "cover": "", 
            "title": "", 
            "updated": 1530456198000, 
            "created": 1530456200000
        }, 
        {
            "consignmentId": 3, 
            "bpId": 2, 
            "targetId": 2, 
            "price": 999, 
            "type": 3, 
            "status": 1, 
            "cover": "https://io.shanren.group/image/cover.jpg", 
            "title": "1元优惠券", 
            "updated": 1530456187000, 
            "created": 1530456188000
        }
    ]
}
```
|   参数名称   | 参数类型 | 是否必传 | 参数说明 |
| :----------: | :------: | :------: | :------: |
| consignmentId |  int  |    是    |  寄售台id |
| bpId |  long  |    是    |  背包id |
| targetId |  int  |    是    |  目标物品id |
| type |  int  |    是    |  1-商品，2-虚拟商品， 3-优惠券 |
| status |  int  |    是    |  1 上架 2 已购买未支付 3 交易完成 4 已下架 |

### 17.4 首页文章列表（与6.1接口一样）


### 17.5 按天获取banner文章列表

- 请求地址：noauth/v1/article/article_list
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| day |  long  |    否   |   当天时间   | 13位时间戳 |

请求结果示例：

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1547179391461,
  "data": [
    {
      "id": 5,
      "title": "养不起，马来西亚考虑提前归还大熊猫？",
      "summary": "“养不起，只有狠心说再见”，马来西亚华文媒体《中国报》18日报道称，因“开支难以为继”，马来西亚政府考虑将2014年从中国租借的大熊猫“兴兴”和“靓靓”提前送回中国。马来西亚内阁19日将召开会议，预计将讨论此事。",
      "cover": "https://liyuquan.cn/static/item/181219/69455f6d-5d97-43bb-8d08-a160d5b88f57.png",
      "created": 1545134864000,
      "type": 1,
      "showTime": 1547143931000
    },
    {
      "id": 7,
      "title": "养不起，马来西亚考虑提前归还大熊猫？",
      "summary": "“养不起，只有狠心说再见”，马来西亚华文媒体《中国报》18日报道称，因“开支难以为继",
      "cover": "https://liyuquan.cn/static/item/181219/69455f6d-5d97-43bb-8d08-a160d5b88f57.png",
      "created": 1545134864000,
      "type": 1,
      "showTime": 1547143931000
    }
  ]
}
```
| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| id | Int | 是 |  文章id |
| title | String | 是 | 文章标题 |
| summary | String | 是 | 文章简介 |
| cover | String | 是 | 文章封面 |
| created | long | 是 | 创建时间 |
|type|byte|是|文章类型 1-banner,2-星座，3-首页普通文章，4-商城普通文章|
|showTime|date|是|展示时间（一般情况banner文章才有展示时间）|


### 17.6 首页节日列表

- 请求地址：noauth/v1/home/festival
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin
- 无参数

请求结果示例：（返回最近5天）

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1547643606628,
  "data": [
    {
      "id": 2, 
      "festival": "元旦节",
      "targetDate": 1547222400000,
      "created": 1547613005000
    },
    {
      "id": 3,
      "festival": "hh节",
      "targetDate": 1547308800000,
      "created": 1547613005000
    },
    {
      "id": 4,
      "festival": "jj节",
      "targetDate": 1547395200000,
      "created": 1547613005000
    },
    {
      "id": 5,
      "festival": "fd节",
      "targetDate": 1547481600000,
      "created": 1547613005000
    },
    {
      "id": 6,
      "festival": "uu节",
      "targetDate": 1547568000000,
      "created": 1547613005000
    }
  ]
}

```
| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| id | Int | 是 |  节日id |
| festival | String | 是 | 节日名称 |
| targetDate | date | 是 | 节日日期（时间戳） |
| created | long | 是 | 创建时间 |




### 17.7 首页黄历

- 请求地址：noauth/v1/home/calendar
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin
- 无参数

请求结果示例：（返回最近5天）

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1552987428616,
  "data": [
    {
      "gongli": "2019.03 星期二",
      "nongli": "二月(小)十三",
      "avoid": "动土 破土 安葬",
      "suit": "结婚 订婚 出行 搬家 入宅",
      "jieri": "花朝节",
      "jieqi24": "惊蛰：3月6日 春分：3月21日",
      "date": 1552987428423
    },
    {
      "gongli": "2019.03 星期一",
      "nongli": "二月(小)十二",
      "avoid": "结婚 安葬",
      "suit": "订婚 祭祀 祈福 开工 求医 治病 动土 纳畜",
      "jieri": "花朝节",
      "jieqi24": "惊蛰：3月6日 春分：3月21日",
      "date": 1552901028423
    },
    {
      "gongli": "2019.03 星期日",
      "nongli": "二月(小)十一",
      "avoid": "祈福 斋醮",
      "suit": "结婚 订婚 祭祀 祈福 出行 动土 上梁 搬家 入宅 破土 安葬",
      "jieri": "中国国医节,国际航海日",
      "jieqi24": "惊蛰：3月6日 春分：3月21日",
      "date": 1552814628423
    },
    {
      "gongli": "2019.03 星期六",
      "nongli": "二月(小)初十",
      "avoid": "诸事不宜",
      "suit": "祭祀 破屋 坏垣 馀事勿取",
      "jieri": "彩蛋节",
      "jieqi24": "惊蛰：3月6日 春分：3月21日",
      "date": 1552728228423
    },
    {
      "gongli": "2019.03 星期五",
      "nongli": "二月(小)初九",
      "avoid": "开工 开光",
      "suit": "结婚 订婚 求医 治病 装修 动土 搬家 入宅 破土 安葬",
      "jieri": "消费者权益日",
      "jieqi24": "惊蛰：3月6日 春分：3月21日",
      "date": 1552641828423
    }
  ]
}



```
| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| gongli|string|是|公历|
|nongli|string|是|农历|
| avoid | string | 是 |  忌讳 |
| suit | string | 是 | 适宜 |
|jieri|stirng|是|节日|
|jieqi24|string|是|24节气|
|date|long|是|时间戳|






## 18. 寄售台
### 18.1 上架寄售台之前商品信息查询
- 请求地址：auth/consignment/info
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| bpId |  Long  |    是    |   无   | 背包id |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531102129342, 
    "data": {
        "newset": 200, 
        "hight": 1000, 
        "low": 100
    }
}
```
|   参数名称   | 参数类型 | 是否必传 | 参数说明 |
| :----------: | :------: | :------:  | :------: |
| newset |  int |    是    | 最新价格 |
| hight |  int  |    是    | 历史最高价格 |
| low |  int  |    是    | 历史最低价格 |

### 18.2 寄售台上架商品
- 请求地址：auth/consignment/putaway
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| bpId |  Long  |    是    |   无   | 背包id |
| price |  int  |    是    |   无   | 上架商品价格 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "商品上架成功", 
    "time": 1531103266862
}
```

### 18.3 寄售台某个商品列表
- 请求地址：noauth/consignment/one_list
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| targetId |  int  |    是    |   无   | 目标物品id |
| type |  int  |    是    |   无   |  1 商品 2虚拟物品 3 优惠券 |
| pageNum |  int  |    是    |   1   | 分页 |
| pageSize |  int  |    是    |   14   | 分页大小 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531103785826, 
    "data": [
        {
            "consignmentId": 6, 
            "bpId": 1, 
            "targetId": 2, 
            "price": 200, 
            "type": 1, 
            "status": null, 
            "cover": "https://io.shanren.group/image/cover.jpg", 
            "title": "魏文侯", 
            "updated": 1531103266000, 
            "created": 1531103266000
        }
    ]
}
```
|   参数名称   | 参数类型 | 是否必传 | 参数说明 |
| :----------: | :------: | :------: | :------: |
| consignmentId |  int  |    是    |  寄售台id |
| bpId |  long  |    是    |  背包id |
| targetId |  int  |    是    |  目标物品id |
| status |  int  |    是    |  1 上架 2 已购买未支付 3 交易完成 4 已下架 |


### 18.4 寄售台商品列表
- 请求地址：noauth/consignment/all_list
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| pageNum |  int  |    是    |   1   | 分页 |
| pageSize |  int  |    是    |   14   | 分页大小 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531104268478, 
    "data": [
        {
            "targetId": 2, 
            "type": 3, 
            "cover": "https://io.shanren.group/image/cover.jpg", 
            "title": "魏文侯", 
            "newset": null, 
            "hight": 999, 
            "low": 999
        }, 
        {
            "targetId": 1, 
            "type": 1, 
            "cover": "https://io.shanren.group/image/cover.jpg", 
            "title": "秦孝公", 
            "newset": null, 
            "hight": 888, 
            "low": 888
        }, 
        {
            "targetId": 2, 
            "type": 1, 
            "cover": "https://io.shanren.group/image/cover.jpg", 
            "title": "魏文侯", 
            "newset": 1000, 
            "hight": 1000, 
            "low": 100
        }
    ]
}
```
|   参数名称   | 参数类型 | 是否必传 | 参数说明 |
| :----------: | :------: | :------:  | :------: |
| newset |  int |    是    | 最新价格 |
| hight |  int  |    是    | 历史最高价格 |
| low |  int  |    是    | 历史最低价格 |
| targetId |  int  |    是    |  目标物品id |
| type |  int  |    是    |   1 商品 2虚拟物品 3 优惠券 |


### 18.5 寄售台商品详情
- 请求地址：noauth/consignment/item_detail
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| consignmentId |  int  |    是    |   无   | 寄售台id |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531104571269, 
    "data": {
        "consignmentId": 1, //寄售台Id
        "itemId": 1,  //物品Id
        "price": 0.01,
        "pictures": [
            "https://io.shanren.group/image/cover.jpg", 
            "https://io.shanren.group/image/cover1.jpg"
        ], 
        "title": "战国战国战国战国战国战国战国战国战国战国战国战国战国战国战国战国战国战国", 
        "description": "东周末年", 
        "detailUrl": "https://43.241.223.169:800/static/product.html?id=1"
    }
}
```

### 18.6 寄售台创建订单
- 请求地址：auth/consignment/order/create
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| consignmentId |  int  |    是    |   无   | 寄售台id |
| payWay |  int  |    是    |   无   | 支付方式,微信 24656 ，支付宝78990，余额 98001 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531105731332, 
    "data": {
        "params": "MYJRWzUWdcu33cOtjBbKmw/M0Eko50Lw5X5wR/wB9xSMvjCs8qBAWTngRc0fK9XFwfNjo31GX5YfigXSEustpapVSyEzm6nBjpe/FZEPD+rj7T9DWfUNbsuWoUPkLIe2ka+uhGVEQbUbFMzQz2otUNOAiKNNtjDtt9YvC3FZ/DZlgmUXKK7exueIRmzmgfQ2drqVqxIJMDkKQ+ByqjPlrJmRw+BIK5wsZzjJJLEEJ+3mCXqR727cephcZ1Xt6EhzLyjo4u4dviKaeQp8iNHkJFYUI/RYpkUe0VMV3Vy1X7uZU0SgFW3EBbsxZxstOpvMK4mrp9aDjeBU15o1B7FplQ==La+xb6eH2maxGPB0vcZfQ3jwgqiC/FKpw05sS1hfICtrcga9NiapY+F7UE8i1Xzkqw3kaURVoyFMQ1mslHOiEcM3+z4SDeB7tESqHu5JiP7mhjUBQ4KvLBIa9WVGI8L0tVcfvmcB9miUcg5A1mKQBzLyt9us0PpoecflaS9cgHrfOKY3eyzmeR+ZSNljWQI7Gc6mcnhSsONZivmdndjBNeAoG9PwbaVGoUTc2kldmyMpkRoC/idgMfYTzQUcbAzR9W/xcrwbWx16gcc5yihmOc6kxqpC+dZxV3P5NSbVAOWczMnn+W4ilH+5Ej1fI8gsUTbex9dorO3E7+G9X1sz3w==||344,344", 
        "orderNo": 518070911106,  //订单号
        "type": 24656 //支付类型
    }
}
```

### 18.7 寄售台订单支付
- 请求地址：auth/consignment/order/pay
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| orderNo |  Long  |    是    |   无   | 订单号 |
| payWay |  int  |    是    |   无   | 支付方式,微信 24656 ，支付宝78990，余额 98001 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1530697397099, 
    "data": {
        "params": "xvz++2WUu79KrQ+0aCyWZt9QYSkkZZycK5xVTUesYJ75C92RZid3PiLzp04liFalM7m8705a6oK/D3UYT5CA1EZKDyAh64rw3Gd3kZ/xCdKJtOZicFhn6RKGnJFxtS9+2R83EqlXrYcn8gan5rWtqchZXOtTXro5ZvREsgEFInmGMgcb+EdfSNPGpYOSRxqcYGcw06A1RPFW1QTjHE1f+y/V+GEiWmkwiakLNSTIjkUI28pzKGTmDx8lZbKUpX/wy+JZ36XO7/HnegFrNtCU+jpp4dXucRcrX8b/yUUJHHPAhx3/kr9NgBgVNmO0uxxsjJcBf9erwGQoeaBjcAfb6g==ixVi3iTdL+lQDk30rlh+JzN58EFt80D8rScDAdRqM5Gwdw+4WHZ3fwkl9+3838rKNtF929Vl5GjmGmIW4J6yjKRfYllgBhO7PekTY+ejiOdTOGwwWm9wFqX77KD4DILs4Vv4Uo6i+HdtGNlCxLTm+L8G2Otms46FC5WW3Yv9f24RkBStLOKq/8Ct47u+C1gdZK9QzyGzFXOxfV6uKHQMlXAS+9gTdAGAcVuIIES80kmoee9ZS/iUjEs5xNVfaQRgBaEWRGJU6U9CNo4O/ZMrRLInVQ6YXBKR0BSNVrqx8iDiYwUvF3yGQ0de4+yaKMaXiC7qUftyrRi4VJrqEjqlyA==W0KKKbwOxS3ecOeY1Uqz/3RPulB2ma8PI96rsWbTqxgCFehXo3ftxV0aCQKz7sJb8Sc2V9mJ8rakjEMdqKDw5gloggXkPlUktRovHkRkdQVx8dFttmPr4Q2ebC9s/E8bLcdhuXQRvst3xJNl/SFszPLHeOC1+xcRIO/vY06H9or+LFZPtTSY3X/iAKB2Aqqnm61dVMoby/FXA1gahzVw/lcXmeY0Yj1PI4wN1PgF/p3wEZC9ec0Tnf5MDPq5rTPVhRPBjMZuf+sszePEXjirw3MmCvEnbPfOIWd2Y2FWNUji6O6sBCWvY87BzVyDgHmpjfe8K03x5kn6dkVudHyGBw==CM9aMfAAY///v9Uq3uryuGLQm6cCSd+cDL3aTWGjBuWgLFuPX/D30QNCWJGOy4huZyNktghTInpabGD+K/OwXQXskIAYx413OCxBLIt6DyyLLlXj5qWY3vrIVCnCH9rYvAYRR/sFhkPpNARg/jaE8NPKHtWlqgCljpIK+ocObw4lwqCIUjXC2xNU5Qc0RrTKtRvyMTZn4hTuEcbIQiSyP1CPXu5olqnLmslYgo8VJjjTwoqAJRSu0ZLf/jaWYdy+CsHTa7yGllKTqOKOY2iQ80gSNxzg0ut1J0RtNv8LDusmBJ3xYB2wVu0zVs572U/BUhD3Rp1uTRhLB3bFYhjGhQ==s1ZNSjVrI5l/v3XJwWtwtogAMKRqt2olxvsfMsPuBCp4xl02iUbsrrbT0L2lWd6d8llgXX5UZ+k+dqPXd6f0h/fivPscZJVEvXmCAcRofvTTakbBoq8xfWeSNszsA3qMiLR0EZiq+ttf/Kn5SoO4Bdno6JIoQV8XvzN9pG/wUsqjbJG3102A7jts3jrhom4zZxl0rND3wpypzZwFtuFtl7Ny47SbCf89Z9YS3iYyOY9kVgRccd8OdfOqjUR4ksyzR/2tB2kWDyniunQ4Uo0eGx3jWzOiAYTu0tCResDrLGbQL1ZmYphiXIfVkfvAW50rmtPnxzSJr1IE/0lblPEavQ==||344,344,344,344,344", 
        "orderNo": 518070411109, 
        "type": 78990
    }
}
```

### 18.8 寄售台下架功能
- 请求地址：auth/consignment/sold_out
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| consignmentId |  int  |    是    |   无   | 寄售台id |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "下架成功", 
    "time": 1531106851621
}
```

### 18.9 寄售台买家/卖家列表

- 请求地址：auth/consignment/bs_list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| user |  byte  |    是    |   无   | 卖家/买家  1-卖家，2-买家 |
| condition |  byte  |    是    |   无   | 商家订单状态 1-全部 ，2-交易中，3-已完成 |
| pageNum |  int  |    是    |   1   | 分页 |
| pageSize |  int  |    是    |   14   | 分页大小 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531107782720, 
    "data": [
        {
            "consignmentId": 3, //寄售台Id
            "bpId": 2,  //背包id
            "targetId": 2,  //目标物品Id
            "price": 999, 
            "type": 2, // 1 商品 2虚拟物品 3 优惠券
            "status": 1, //1 上架 2 已购买未支付 3 交易完成 4 已下架
            "cover": "https://io.shanren.group/image/cover.jpg", 
            "title": "1元优惠券", 
            "updated": 1530456187000, 
            "created": 1530456188000
        }, 
        {
            "consignmentId": 4, 
            "bpId": 3, 
            "targetId": 1, 
            "price": 888, 
            "type": 1, 
            "status": 1, 
            "cover": "", 
            "title": "", 
            "updated": 1530456198000, 
            "created": 1530456200000
        }, 
        {
            "consignmentId": 6, 
            "bpId": 1, 
            "targetId": 2, 
            "price": 200, 
            "type": 1, 
            "status": 1, 
            "cover": "https://io.shanren.group/image/cover.jpg", 
            "title": "魏文侯", 
            "updated": 1531103266000, 
            "created": 1531103266000
        }
    ]
}
```

## 19 人情账簿

### 19.1 人情账簿总的收支数据

- 请求地址：auth/v1/mall/record/inex
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

```js
{
    errCode: 0, 
    result: 0, 
    time: 1531122790730, 
    data: {
        incoming: 10000, // 收入
        outgoings: 64534 // 汁出
    }
}
```

### 19.2 人情账簿列表查询

- 请求地址：auth/v1/mall/record/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| starting | long | 否 | 无 | 开始时间（13位时间戳） |
| ending | long | 否 | 无 | 结束时间（13位时间戳） |
| obType | String | 否 | 无 | 类型 ：个人、家庭 |
| pageNum | int | 否 | 1 | 分页 |
| pageSize | int | 否 | 14 | 分页大小 |

* 请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    time: 1531129307850, 
    data: {
        records: [
            {
                recordId: 2, // 记录id
                inoutType: 1, // 收支类型 1 汁出 2 收入
                amount: 54534, // 金额
                event: "那年花开", // 赠送事件
                greetting: "一厢情愿的不舍",  // 祝福语
                obType: "个人", // 类型
                created: 1530847416000 // 时间
            }, 
            {
                recordId: 1, 
                inoutType: 1, 
                amount: 10987, 
                event: "什么节", 
                greetting: "么么哒", 
                obType: "个人", 
                created: 1530691318000
            }, 
            {
                recordId: 1, 
                inoutType: 2, 
                amount: 10000, 
                event: "什么节", 
                greetting: "么么哒", 
                obType: "个人", 
                created: 1530691318000
            }
        ], 
        inout: { // 查询条件下的收支统计
            incoming: 10000, // 收入总数 
            outgoings: 65521 // 汁出总数
        }
    }
}
```



## 20 背包—礼物互换
### 20.1 用户添加想要交换的物品到交换记录

- 请求地址：auth/v1/exchange/add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| friendUserId | int | 是 | 无 | 好友Id |
| exchangeBpId | String | 是 | 无 | 想交换的礼物 背包Id用逗号隔开 |
| wantBpId | String | 是 | 无 | 想要的礼物  背包Id用逗号隔开 |


* 请求结果示例

```js
{
    "errCode": 0, 
    "result": 0, 
    "msg": "用户添加交换礼物成功", 
    "time": 1531645689773, 
    "data": "http://baidu.com"
}
```

### 20.2 好友提交要交换的物品

- 请求地址：auth/v1/exchange/friend_add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| giftExchangeId | int | 是 | 无 | 物品交换记录Id |
| submitId | String | 是 | 无 | 提交的物品Id 背包Id用逗号隔开 |


* 请求结果示例

```js
{
    "errCode": 0, 
    "result": 0, 
    "msg": "礼物交换添加成功", 
    "time": 1531646184612, 
    "data": "http://baidu.com"
}
```


### 20.3 确认交换礼物

- 请求地址：auth/v1/exchange/confirm
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| giftExchangeId | int | 是 | 无 | 物品交换记录Id |
| operation | String | 否 | 0 | 操作 0-默认，1-确认交换，2-取消交换 |


* 请求结果示例

```js
{
    "errCode": 0, 
    "result": 0, 
    "msg": "礼物交换成功", 
    "time": 1531646446320
}
```

### 20.4 礼物交换记录

- 请求地址：auth/v1/exchange/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| status | byte | 是 | 无 | 状态，0-全部，1-交易中，2-已完成， |
| pageNum | int | 否 | 1 | 分页 |
| pageSize | int | 否 | 14 | 分页大小 |


* 请求结果示例

```js
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531646566782, 
    "data": [
        {
            "id": 2, 
            "userId": 1, 
            "friendUserId": 4,   //好友用户Id
            "friendNickname": "大秦", //好友昵称
            "friendAvatar":   //好友头像 "https://io.shanren.group/static/upload/image/avatar.jpg", 
            "type": 1,  //1-用户向好友提出交换礼物 ，2-好友向用户提出交换礼物
            "status": 3, //礼品添加状态，1-用户提交，2-好友提交，3-已完成，4-交换失败
            "created": 1531645688000
        }, 
        {
            "id": 1, 
            "userId": 1, 
            "friendUserId": 4, 
            "friendNickname": "大秦", 
            "friendAvatar": "https://io.shanren.group/static/upload/image/avatar.jpg", 
            "type": 1, 
            "status": 3, 
            "created": 1531559723000
        }
    ]
}

```

### 20.5 礼物交换详情

- 请求地址：auth/v1/exchange/detail
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| giftExchangeId | int | 是 | 无 | 礼物交换记录Id |


* 请求结果示例

```js
{
    "errCode": 0, 
    "result": 0, 
    "time": 1531646787701, 
    "data": {
        "id": 1, 
        "userId": 1, 
        "friendUserId": 4,  //好友用户id
        "friendNickname": "大秦",  //好友用户昵称
        "friendAvatar": "https://io.shanren.group/static/upload/image/avatar.jpg", 
        "type": 1, //1-用户向好友提出交换礼物 ，2-好友向用户提出交换礼物
        "status": 3,  //礼品添加状态，1-用户提交，2-好友提交，3-已完成，4-交换失败
        "created": 1531559723000, 
        "exchangeGifts": [ //用户交换的物品
            {
                "bpId": 1, 
                "targetId": 2, //目标物品Id
                "price": 100, 
                "title": "魏文侯",  
                "cover": "https://io.shanren.group/static/upload/image/cover.jpg", 
                "description": "东周末年", 
                "brand": "公牛",  //牌子
                "type": 1, 
                "quantity": 4
            }, 
            {
                "bpId": 2, 
                "targetId": 2, 
                "price": 100, 
                "title": "2元现金券", 
                "cover": "https://io.shanren.group/static/upload/image//item/180711/f5b7f1d6-3c18-457b-b920-b1a941337862.png", 
                "description": "{
 \"type\": \"优惠券\",
 \"discunt\":\"1元\",
 \"decription\":\"仅限购买三只松鼠\"
}", 
                "brand": "七牛", 
                "type": 3, 
                "quantity": 3
            }
        ], 
        "wantGifts": [     //用户想要的物品
            {
                "bpId": 7718071115125, 
                "targetId": 1, 
                "price": 0.01, 
                "title": "秦孝公", 
                "cover": "https://io.shanren.group/static/upload/image/cover.jpg", 
                "description": "东周末年", 
                "brand": "公牛", 
                "type": 1, 
                "quantity": 1
            }, 
            {
                "bpId": 7718070611101, 
                "targetId": 2, 
                "price": 100, 
                "title": "2元现金券", 
                "cover": "https://io.shanren.group/static/upload/image//item/180711/f5b7f1d6-3c18-457b-b920-b1a941337862.png", 
                "description": "{
 \"type\": \"优惠券\",
 \"discunt\":\"1元\",
 \"decription\":\"仅限购买三只松鼠\"
}", 
                "brand": "七牛", 
                "type": 3, 
                "quantity": 1
            }
        ], 
        "submitGifts": [    //好友提交的物品
            {
                "bpId": 7718071315102, 
                "targetId": 1, 
                "price": 1000, 
                "title": "秦孝公", 
                "cover": "https://io.shanren.group/static/upload/image/cover.jpg", 
                "description": "东周末年", 
                "brand": "公牛", 
                "type": 1, 
                "quantity": 1
            }, 
            {
                "bpId": 7718071315101, 
                "targetId": 2, 
                "price": 100, 
                "title": "2元现金券", 
                "cover": "https://io.shanren.group/static/upload/image//item/180711/f5b7f1d6-3c18-457b-b920-b1a941337862.png", 
                "description": "{
 \"type\": \"优惠券\",
 \"discunt\":\"1元\",
 \"decription\":\"仅限购买三只松鼠\"
}", 
                "brand": "七牛", 
                "type": 3, 
                "quantity": 1
            }
        ]
    }
}
```


## 21 整点福利
### 21.1 获取整点福利

- 请求地址：noauth/v1/home/welfare
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

无参数


* 请求结果示例

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1550731078370,
  "data": {
    "id": 1,  //福利id
    "type": 3, //1商品 2.虚拟商品，3优惠券
    "targetId": 1, //type= 1时商品skuId,type= 2时虚拟商品id，type= 3时优惠券id
    "quantity": 10, //总数
    "targetDate": 1550673648000, //福利发放时间
    "startTime": 1550533296000, //福利显示开始时间
    "endTime": 1550878920000, //福利显示结束时间
    "count": 6, //礼物剩余数量
    "cover": "/item/190219/1eb62a35-c09f-4053-a184-b752320f6bfc.gif", //图片
    "title": "50", //标题
    "brand": "50" //品牌
  }
}


```


### 21.2 领取整点福利礼物

- 请求地址：auth/welfare/confirm
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |



* 请求结果示例

```js
{
  "errCode": 0,
  "result": 0,
  "msg": "领取成功",
  "time": 1550716732581
}

```


## 22 备忘录2.0

### 22.1 添加备忘录事件类型

- 请求地址：auth/memo/affair2/event/add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
|name|string|是|无|事件类型|

* 请求示例

```js
{
	"errCode": 0,
	"result": 0,
	"msg": "添加成功!",
	"time": 1529643310344
}
```


### 22.2 修改备忘录事件类型

- 请求地址：auth/memo/affair2/event/update
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
|name|string|是|无|事件类型|
|eventId|int|是|无|事件类型id|

* 请求示例

```js
{
	"errCode": 0,
	"result": 0,
	"msg": "修改成功!",
	"time": 1529643310344
}
```


### 22.3 删除备忘录事件类型

- 请求地址：auth/memo/affair2/event/del
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
|eventId|int|是|无|事件类型id|

* 请求示例

```js
{
	"errCode": 0,
	"result": 0,
	"msg": "删除成功!",
	"time": 1529643310344
}
```



### 22.4 备忘录事件类型列表

- 请求地址：auth/memo/affair2/event/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |


* 请求示例

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1552357341531,
  "data": [
    {
      "id": 1,  //事件类型id
      "userId": 0,   //用户id（0为系统默认的事件）
      "name": "生日",  //事件类型名称
      "created": 1552266313000, //创建时间
      "updated": 1552266314000 //更新时间
    },
    {
      "id": 2,
      "userId": 6,
      "name": "结婚",
      "created": 1552266325000,
      "updated": 1552266327000
    }
  ]
}

```



### 22.5 添加备忘录事件2.0

- 请求地址：auth/memo/affair2/add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| targetTime | long | 是 | 无 | 备忘时间（13位时间戳） |
| detail | string | 是 | 无 | 活动详情 |
| eventTypeId | int | 否 | 1 | 事件类型id |
| users | string | 否 | 无 | 邀请的好友的user id集合，多个用,隔开 |
|isCirculation|byte|否|0|添加的事件是否循环 0-不循环 1-按周循环，2-按月循环，3-按年循环|

* 请求示例

```js
{
	"errCode": 0,
	"result": 0,
	"msg": "添加成功!",
	"time": 1529643310344
}
```


### 22.6 修改备忘录事件

- 请求地址：auth/memo/affair2/modify_affair
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id | int | 是 | 无 | 备忘录id |
| targetTime | long | 是 | 无 | 备忘时间（13位时间戳） |
| detail | string | 是 | 无 | 活动详情 |
| eventTypeId | int | 否 | 1 | 事件类型id |
| users | string | 否 | 无 | 邀请的好友的user id集合，多个用,隔开 |
|isCirculation|byte|否|0|添加的事件是否循环 0-不循环 1-按周循环，2-按月循环，3-按年循环|


* 请求示例

```js
{
	"errCode": 0,
	"result": 0,
	"msg": "修改成功!",
	"time": 1529643310344
}
```



### 22.7 删除一条备忘录

- 请求地址：auth/memo/affair2/del
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id | int | 是 | 无 | 备忘录id |


* 请求示例

```js
{
	"errCode": 0,
	"result": 0,
	"msg": "删除成功!",
	"time": 1529643310344
}
```


### 22.8 获取用户的备忘录列表

- 请求地址：auth/memo/affair2/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| start | long | 否 | 无 | 开始时间戳 |
| end | long | 否 | 无 | 结束时间戳 |

* 请求结果示例

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1552374188046,
  "data": [
    {
      "id": 1,   //备忘录id
      "userId": 6, //创建者id
      "targetTime": 1552374073000, //备忘时间
      "isCirculation": 0, //0-不循环
      "detail": "晚会", //事件详情
      "type": 1,  // 1自己创建，2-被邀请，3-节日事件
      "users": "1,31", //被邀请者用户id 
      "created": 1552374107000, //创建时间
      "nickname": "林琴", //创建者用户昵称
      "name": "", //type等于1，2时没用到
      "count": 2, //邀请人数
      "avatar": "https://wx.qlogo.cn/mmopen/vi_32/cLhvDgpVNMm24pZLQn9NJLvTbribW3ymS4dXSctqaaKWhF7NJcI1Nicqp0QGw2jjVPJBLBjGStsYkaefM5fiaq5SA/132" //创建着头像
     "eventTypeId": 1  //事件类型id
      "eventTypeName": "生日" //事件类型名称
    },
    {
      "id": 1,   //type等于3时，节日事件id 
      "userId": null,
      "targetTime": 1552002895000, //备忘时间
      "isCirculation": null,  //节日事件不循环
      "detail": "女神节i，女王节，，，",  //节日事件简介
      "type": 3, //1自己创建，2-被邀请，3-节日事件
      "users": "", 
      "created": 1552262181000,
      "nickname": "",
      "name": "妇女节", //节日事件名称
      "count": null, 
      "avatar": "https://liyuquan.cn/staticcover.jpg" //节日事件标题
     "eventTypeId": null  //事件类型id
    },
    {
      "id": 2,
      "userId": 6,
      "targetTime": 1552374073000,
      "isCirculation": 1,
      "detail": "晚会hhh",
      "type": 1,
      "users": "1,31",
      "created": 1552374123000,
      "nickname": "林琴",
      "name": "",
      "count": 2,
      "avatar": "https://wx.qlogo.cn/mmopen/vi_32/cLhvDgpVNMm24pZLQn9NJLvTbribW3ymS4dXSctqaaKWhF7NJcI1Nicqp0QGw2jjVPJBLBjGStsYkaefM5fiaq5SA/132"
     "eventTypeId": 1  //事件类型id
     "eventTypeName": "生日" 
    }
    ]
}
```


| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| :---: | :---: | :---: | :---: | :---: |
| id | int | 是 | 备忘录id(type = 3时 id是节日事件id)  |
| userId | int | 是 | 此条备忘录的创建者id |
| targetTime | long | 是 | 备忘时间 |
| detail | string | 是 | 备忘信息 |
| type | int | 否 | 1自己创建，2-被邀请，3-节日事件 |
| nickname | string | 是 | 创建者昵称 |
| avatar | string | 是 | 创建者头像 |





### 22.9 查看好友备忘录

- 请求地址：auth/memo/affair2/list/friend
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| friendUserId | int | 是 | 无 | 好友用户id |
| start | long | 否 | 无 | 开始时间戳 |
| end | long | 否 | 无 | 结束时间戳 |

* 请求示例（和22.8一致）



### 22.10 节日事件详情

- 请求地址：auth/memo/affair2/festival_detail
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id | int | 是 | 无 | 节日事件id |


* 请求示例

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1552380833476,
  "data": {
    "id": 1,  //节日事件id
    "picture": "ava.jpg", //详情封面
    "title": "女王大人", //详情标题
    "targetTime": 1552002895000, //节日时间
    "detail": "哈哈哈哈哈",  //详情
    "created": 1552262181000, //创建时间
    "yi": "祭祀 裁衣 冠笄 安床 交易 立券 开池 补垣 塞穴 入殓 破土 启钻 安葬 谢土 除服 成服",  //宜
    "ji": "结婚 掘井 探病 开工 开光 栽种" //忌
  }
}
```



### 22.11 节日事件详情页商品列表

- 请求地址：auth/memo/affair2/festival_item
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id | int | 是 | 无 | 节日事件id |


* 请求示例

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1552383887303,
  "data": [
    {
      "itemId": 78, //商品id
      "cover": "https://liyuquan.cn/static/item/190130/ 60c775fc-ecb6-4f4e-9219-eb2a4ad1812d.jpg", //商品封面
      "title": "【官方正品】纪梵希恒颜清透粉底液 持久遮瑕自然修饰", //商品标题
      "price": 480.00 //商品价格
    }
  ]
}

```


### 22.12 节日事件详情页好友列表

- 请求地址：auth/memo/affair2/friend_list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |



* 请求示例(返回5个好友)

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1552384278846,
  "data": [
    {
      "userId": 6, //用户id（自己）
      "friendUserId": 1,//好友id
      "heartNum": 4, //互动值
      "nickname": "路遥", //好友昵称
      "avatar": "https://liyuquan.cn/staticorder/comment/20181231/1546231284130051-600-600.jpg" //好友头像
    },
    {
      "userId": 6,
      "friendUserId": 8,
      "heartNum": 2,
      "nickname": "陈红兵",
      "avatar": "https://wx.qlogo.cn/mmopen/vi_32/OwZuBRbVUkx404NMZSBD01pJPKxB1r1aGx7aKHje5IJN2b8RWOOeLZziantR36BsHY4d5QacWibvzllOMRSEvUvA/132"
    },
    {
      "userId": 6,
      "friendUserId": 31,
      "heartNum": 1,
      "nickname": "淡水鱼",
      "avatar": "http://thirdwx.qlogo.cn/mmopen/vi_32/7mydP7r5lbHTuUuMtv36ta67UPGSicjQRwwXx94Yf2ugHuI0tPmJZXlGt27yKkwF0CJ60aBiaTCIlyLvqK4ibibEnA/132"
    },
    {
      "userId": 6,
      "friendUserId": 42,
      "heartNum": 1,
      "nickname": "0830311",
      "avatar": "https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqBK56mMxh3z4GyMrSgCpzjAH6QiaD2AG0h4FpgMu5QQ8ZqZmsCKCYwsrrZyecELhicaBppLsxALPtg/132"
    },
    {
      "userId": 6,
      "friendUserId": 25,
      "heartNum": 0,
      "nickname": "柒橼",
      "avatar": "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL6UhyQbwfpUzaovb4ltJf2FImjsjEyibA8VicNrbZSmjzhXskgB2jH0Jmx6gURvYebAv1DYBErtaRA/132"
    }
  ]
}


```


## 23 用户礼花

### 23.1 用户礼花详情

- 请求地址：auth/v1/fireworks/detail
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

* 请求示例

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1554280794095,
  "data": {
    "id": 1,
    "userId": 6,
    "count": 0,
    "allCount": 0,
    "created": 1554280783000,
    "updated": 1554280783000
  }
}

```

|   参数名称   | 参数类型 | 是否必传 |  参数说明 |
| :----------: | :------: | :------: | :------: |
| id |  int  |    是    |  礼花id |
|userId|int|是|用户id|
|count|Int|是|礼花剩余数量|
|allCount|int|是|礼花历史总数|
|created|date|是|创建时间|
|updated|date|是|更新时间|



### 23.2 礼花-我的团队

- 请求地址：auth/v1/fireworks/user_list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

* 请求示例

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1554283653914,
  "data": [
    {
      "id": 1, //邀请记录地
      "userId": 1,  //用户id（被邀请者）
      "parentUserId": 6, //用户id(邀请者，用户本人)
      "created": 1554252870000, //创建时间
      "avatar": "https://liyuquan.cn/staticorder/comment/20181231/ 1546231284130051-600-600.jpg", //用户头像
      "nickname": "路遥" //用户昵称
    },
    {
      "id": 2,
      "userId": 27,
      "parentUserId": 6,
      "created": 1554254174000,
      "avatar": "http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEIt5F3S7XLUTopicPgf2YoWkjM9aFL9RGfLq9qruZOic6lxZxJLrI21KmXSUkYsjznDEGvVDXVAfKSQ/132",
      "nickname": "年锐"
    }
  ]
}
```



### 23.3 礼花-礼花记录

- 请求地址：auth/v1/fireworks/earn_record
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

* 请求示例

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1554342462716,
  "data": [
    {
      "id": 1, //记录id
      "userId": 6, //用户id
      "describe": "邀请好友时良奖励", //说明
      "count": 10, //本次礼花交易数量
      "targetId": 1, //type=1时邀请记录id,type=2,3时商品订单id
      "type": 1, //1邀请好友，2好友消费，3自己购买商品
      "created": 1554313207000, //创建时间
      "updated": 1554313211000 //更新时间
    },
    {
      "id": 2,
      "userId": 6,
      "describe": "邀请好友年锐奖励",
      "count": 10,
      "targetId": 1,
      "type": 2,
      "created": 1554313207000,
      "updated": 1554313211000
    }
  ]
}

```


### 23.4 礼花-礼花剩余数量和比例

- 请求地址：auth/v1/fireworks/number
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |

* 请求示例

```js
{
  "errCode": 0,
  "result": 0,
  "time": 1554349820217,
  "data": {
      "userId": 6 // 用户id
    "proportion": 10, //礼花转换成金额比例  1元=10礼花
    "count": 4  // 礼花剩余数量
  }
}

```