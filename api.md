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


## 目录
<span id="m"> </span>

[TOC]


## 1 接口说明
### 1.1 接口访问地址
https://io.shanren.group/app/
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



### 2.2 第三方账号绑定手机号

- 请求地址：noauth/user/bindPhone
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

| 参数名称 | 参数类型 | 是否必传 | 默认值 |    参数说明     |
| :------: | :------: | :------: | :----: | :-------------: |
|  phone   |  String  |    是    |   无   |      电话       |
|   key    |  String  |    是    |   无   | 缓存openId的key |





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
        "avatar": "avatar.jpg", 
        "nickname": "三生石", 
        "age": 21, 
        "gender": 2, 
        "signature": "没有", 
        "district": "昆明", 
        "status": 1, 
        "sentPwd": "123456", 
        "wxid": "123", 
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
|    avatar    |  String  |    是    |   无   |           头像            |
|   nickname   |  String  |    是    |   无   |           昵称            |
|     age      |   int    |    是    |   无   |           年龄            |
|    gender    |   int    |    是    |   0    | 性别（默认0），1-男，2-女 |
|  signature   |  String  |    是    |   无   |         个性签名          |
|   district   |  String  |    是    |   无   |           地区            |

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
| type |  int  |    是    |   无   | 验证码类型 |

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
            type: 2, 
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
| ids | string | 是 | 无 | 选中的礼物偏好id集合，多个用,隔开 |


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
            "id": 3,
            "bankId": 1,
            "logo": "http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132",
            "bankName": "中国银行",
            "depositBank": "青年路支行",
            "cardHolder": "王保国",
            "cardNo": "6217003955026223887"
        },
        {
            "id": 1,
            "bankId": 4,
            "logo": "http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132",
            "bankName": "建设银行",
            "depositBank": "建设路支行",
            "cardHolder": "yy1",
            "cardNo": "6217003895276001039"
        }
    ]
}
```

| 参数名称    | 参数类型 | 是否必传 |         参数说明          |
| ----------- | :------: | :------: | :-----------------------: |
| errCode     |   Int    |    是    | 错误码 0 标识成功获取数据 |
| data        |  Object  |    否    |       成功返回数据        |
| id          |   Int    |    是    |       用户银行卡id        |
| bankId      |   Int    |    是    |          银行id           |
| bankName    |  String  |    是    |         银行名称          |
| logo        |  String  |    是    |         银行logo          |
| depositBank |  String  |    是    |        开户行名称         |
| cardHolder  |  String  |    是    |        持卡人姓名         |
| cardNo      |  String  |    是    |         银行卡号          |

### 3.5 提现--添加用户银行卡

- 请求地址：auth/v1/bankCard/add
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 |  参数说明  |
| :----------: | :------: | :------: | :----: | :--------: |
| access_token |  string  |    是    |   无   |  访问令牌  |
| depositBank  |  string  |    是    |   无   | 开户行名称 |
|  cardHolder  |  string  |    是    |   无   | 持卡人姓名 |
|    cardNo    |  string  |    是    |   无   |  银行卡号  |
|    bankId    |   Int    |    是    |   无   |   银行id   |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "msg": "添加成功",
    "time": 1528773060163
}
```

### 3.6 提现--删除用户银行卡

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
            "describe": "申请提现"
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
| amount   | decimal  |    是    |                        提现金额                        |
| status   |   Int    |    是    | 提现状态，1-申请提现，2-提现中，3-提现成功，4-提现失败 |
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

## 4. 全国行政区查询

### 4.1 获取行政区列表

- 请求地址：/auth/district/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

|   参数名称   | 参数类型 | 是否必传 | 默认值 |             参数说明              |
| :----------: | :------: | :------: | :----: | :-------------------------------: |
| access_token |  string  |    是    |   无   |             访问令牌              |
|    level     |  string  |    是    |   无   | 行政区级别 province city district |
|   pAdcode    |   int    |    是    |   无   |            父级行政区id             |

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
    "time": 1528769843718,
    "data": [
        {
            "id": 3,
            "consigneeName": "李志国1",
            "phone": "13906659842",
            "addressDetail": "云南省昆明市盘龙区129号31栋3单元5031",
            "status": 1
        },
        {
            "id": 1,
            "consigneeName": "小明",
            "phone": "13965869852",
            "addressDetail": "云南省昆明市五华区一二一大街126号1单元001",
            "status": 1
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
| status | Int | 是 | 地址状态 1.不是默认地址 2.是默认地址 |

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

- 请求地址：auth/v1/article/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
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
            "created": 1528769929000
        },
        {
            "id": 3,
            "title": "美丽的风景3",
            "summary": "这是关于一篇美丽的风景的报道3",
            "cover": "http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132",
            "created": 1528769929000
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

### 6.2 文章详情

- 请求地址：auth/v1/article/detail
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yy

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id |  Int  |    是    |   无   | 文章id |

请求结果示例：

```json
{
    "errCode": 0,
    "result": 0,
    "time": 1528783642255,
    "data": {
        "title": "美丽的风景3",
        "created": 1528769929000,
        "detail": ""
    }
}
```

| 参数名称 | 参数类型 | 是否必传 | 参数说明 |
| ------- |:------:|:------:|:------:|
| errCode | Int | 是 | 错误码 0 标识成功获取数据 |
| data | Object | 否 | 成功返回数据 |
| title | String | 是 | 文章标题 |
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
    "time": 1530514839657,
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
    "time": 1528871352790, 
    "data": [
        {
            "id": 1, 
            "pid": 0, 
            "name": "手机", 
            "status": 1, 
            "sort": 1, 
            "icon": "", 
            "created": 1528770795000, 
            "updated": 1528770798000
        }, 
        {
            "id": 2, 
            "pid": 0, 
            "name": "家电", 
            "status": 1, 
            "sort": 1, 
            "icon": "", 
            "created": 1528771771000, 
            "updated": 1528771773000
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
| classes  |   int    |    否    |   0    | 查询类型 0-默认，1-精选，2-热门 |
|  gender  |   int    |    否    |   0    |   筛选性别 0-默认，1-男，2-女   |
|  minAge  |   int    |    否    |   无   |            最小年龄             |
|  maxAge  |   int    |    否    |   无   |            最大年龄             |
| minPrice |  double  |    否    |   无   |            最低价格             |
| maxPrice |  double  |    否    |   无   |            最高价格             |
| eventId  |   int    |    否    |   无   |             事件id              |
| pageNum  |   int    |    否    |   1    |              分页               |
| pageSize |   int    |    否    |   14   |            分页大小             |

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
| description |    String    |    是    |          商品属性          |
| pictures    | List<String> |    是    |        轮播图片数组        |
| detailUrl   |    String    |    是    |        商品详情地址        |
| isCollect   |     Int      |    是    | 是否收藏 1.已收藏 2.未收藏 |

### 6.10 商品评论

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
            "cover": "cover.jpg",
            "price": 600
        },
        {
            "id": 7,
            "itemId": 2,
            "title": "小黄人",
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
## 7. 购物车

### 7.1 购物车列表

- 请求地址：auth/cart/list
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
    "time": 1528945325582, 
    "data": [
        {
            "itemId": 1, 
            "skuId": 1, 
            "cover": "cover.jpg", 
            "title": "秦孝公", 
            "quantity": 1, 
            "price": 1000, 
            "created": 1528884318000
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
|    skuId     |   int    |    是    |   无   | 商品最小销售单元id |

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

### 7.6 创建商品订单
- 请求地址：auth/item/order/create
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
|  access_token  |   String    |    是    |   无   |  访问令牌  |
| skus | String | 是 | 无 | json数组，数组格式，[{"skuId":skuId,"quantity":quantity}] |
| payWay | int | 是 | 无 | 支付方式，微信 24656 ，支付宝 78990 |

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
| payWay | int | 是 | 无 | 支付方式，微信 24656 ，支付宝 78990 |

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
                    "quantity": 2
                }, 
                {
                    "itemId": 1, 
                    "skuId": 2, 
                    "title": "魏文侯", 
                    "price": 1000, 
                    "cover": "https://io.shanren.group/image/cover.jpg", 
                    "quantity": 2
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
                    "quantity": 2
                }, 
                {
                    "itemId": 1, 
                    "skuId": 2, 
                    "title": "魏文侯", 
                    "price": 1000, 
                    "cover": "https://io.shanren.group/image/cover.jpg", 
                    "quantity": 2
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

## 8 图片上传

### 8.1 base64上传

- 请求地址：noauth/file/upload
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
|  base64  |   String    |    是    |   无   |  图片base64编码  |
| module | int | 是 | 无 | 模块 |


### 8.2 multipart方式上传

- 请求地址：noauth/file/upload/multi
- 服务协议：HTTP/POST
- 是否需要身份认证：否
- 作者：linqin

| 参数名称 | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :------: | :------: | :------: | :----: | :------: |
| module | int | 是 | 无 | 模块 |

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
| remark | string | 是 | 无 | 备注名称 |
| groupId | int | 是 | 无 | 分组id |

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

- 请求地址：auth/friend/group/add
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
| key | string | 是 | 无 | 搜索关键字，目前只支持手机号搜索 |

* 请求结果示例

```js
{
    errCode: 0, 
    result: 0, 
    time: 1529590714779, 
    data: {
        userId: 1, 
        avatar: "avatar.jpg", 
        nickname: "什么", 
        isFriend: 1 // 是否是好友 1 是 2 不是
    }
}
```

## 11 备忘录

### 11.1 添加活动

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



### 11.2 添加事件

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

### 11.3 获取用户的备忘录列表

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



### 11.4 查看备忘录详情

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



### 11.5 删除一条备忘录

- 请求地址：auth/memo/del
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| id | int | 是 | 无 | 备忘录id |
| type | int | 是 | 无 | 1 活动 2 事件 |


### 11.6 修改活动信息

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



### 11.7 修改事件

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


### 11.8 查看好友通讯录

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

## 12 通讯录=秀秀

### 12.1 发布秀秀

- 请求地址：auth/moment/publish
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| content | string | 是 | 无 | 文字内容 |
| medias | string | 否 | 无 | 图片或视频json字符串 |
| showGift | int | 否 | 2 | 1 显示最近收到的礼物 2 不显示 |

* medias 结构

```js
{
	"type": 1, // 1 图片 2 视频
    "url":"pic.jpg"  // 图片或视频的相对地址
}
```

* 请求示例（和11.3一致）



### 12.2 赞|取消赞（好友才能操作）

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



### 12.3 评论（好友才能操作）

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




### 12.4 回复）

- 请求地址：auth/moment/comment
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| commentId | int | 是 | 无 | 评论id |
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


### 12.5 秀秀列表）

- 请求地址：auth/moment/list
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：yichen

|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
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
            createUserId: 4, 
            content: "既然不快乐又不喜欢这里", 
            medias: [
                {
                    type: 1, 
                    url: "https://io.shanren.group/image/avatar.jpg"
                }
            ], 
            created: 1529988931000, 
            updated: 1529988931000, 
            selfUserId: 4, 
            nickname: "大秦帝国", 
            avatar: "https://io.shanren.group/image/avatar.jpg", 
            remark: "", 
            relationship: "", 
            praiseUsers: [
                {
                    praiseUserId: 4, 
                    momentId: 1, 
                    avatar: "https://io.shanren.group/image/avatar.jpg"
                }
            ], 
            comments: [
                {
                    commentId: 4, 
                    content: "何时去大理", 
                    momentId: 1, 
                    type: 2, 
                    createUser: {
                        userId: 4, 
                        nickname: "大秦帝国", 
                        remark: "", 
                        relationship: "", 
                        avatar: "https://io.shanren.group/image/avatar.jpg"
                    }, 
                    targetUser: {
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
| comments | 评论泪飙 |
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

## 13. 商品提货
### 13.1 商品提货订单
- 请求地址：auth/receive/item/order
- 服务协议：HTTP/POST
- 是否需要身份认证：是
- 作者：linqin



|   参数名称   | 参数类型 | 是否必传 | 默认值 | 参数说明 |
| :----------: | :------: | :------: | :----: | :------: |
| access_token |  string  |    是    |   无   | 访问令牌 |
| bpItemId | int | 是 | 无 | 背包商品id |
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
            "status": 3, 
            "created": 1530242782000, 
            "updated": 1530242782000
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
| pictures | String | 是 | 无 | 评论照片 |

请求结果示例：

```json
{
    "errCode": 0, 
    "result": 0, 
    "msg": "评论成功", 
    "time": 1530443384551
}
```




