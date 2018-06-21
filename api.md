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
|    payWay    |   int    |    是    |   无   | 支付方式 |

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

* 加密规则说名

```js
String apiKey; // 接口签名的apiKey
String pwd;    // 原密码MD5 32位大写
String s1;     // 3.9中返回的随机数字
String time;   // 参与接口签名的随机数
// 拼接密码
pwd = String.format("%s@%s", pwd, Utils.toMD5(String.format("%spinjie%s", time, s1)));
// 拼接加密的密钥
int len = apiKey.length();
// 取出随机数字的第一位
int first = s1.charAt(0) - 48;
// 去除apiKey中的第first位
String seed = String.format("%s%s%s%s", time, apiKey.substring(0, first),
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
|   pAdcode    |   int    |    是    |   无   |            级行政区id             |

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

- 请求地址：auth/v1/reAddress/add
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
    "time": 1528784375324,
    "data": [
        {
            "id": 1,
            "name": "小苹果",
            "cateName": "鲜花",
            "price": 2,
            "description": "象征和平的小苹果",
            "cover": "http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132"
        },
        {
            "id": 3,
            "name": "小蝴蝶",
            "cateName": "动物",
            "price": 4,
            "description": "执子之手与子偕老",
            "cover": "http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132"
        },
        {
            "id": 2,
            "name": "一片四叶幸运草",
            "cateName": "鲜花",
            "price": 3,
            "description": "等待爱情出现会幸福",
            "cover": "http://thirdwx.qlogo.cn/mmopen/vi_32/jhXsk4K6SZs58GvXyrPichgxlDv6y4IYrrKN5GCA1UTvHRKbRGtiac2SxmGMYibJSvCZzcLhNmQEykDgXTTzkPOXQ/132"
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
| cateName | String | 是 | 虚拟商品分类名称 |
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




