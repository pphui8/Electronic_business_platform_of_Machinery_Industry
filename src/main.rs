#![allow(unused)]
#![allow(non_snake_case)]
use rocket::launch;
use rocket::routes;
use rocket::serde::json::serde_json::json;
use rocket::serde::json::serde_json::to_string;
use rocket::serde::json::{Json, Value};
use rocket::serde::{Deserialize, Serialize};
use rocket::{catch, catchers, get, post};

#[post("/")]
async fn index() -> Value {
    json!({
        "status": 200
    })
}

#[post("/")]
async fn updateuser() -> Value {
    json!({
    "status": 0,
    "msg": "用户信息修改成功！",
    "data": {
        "id": 1,
        "account": "admin",
        "password": "e10adc3949ba59abbe56e057f20f883e",
        "email": "546415155@qq.com",
        "age": 18,
        "sex": 0,
        "del": 0,
        "phone": "17844545452",
        "question": "1",
        "asw": "1",
        "role": 1,
        "create_time": "1517672134000",
        "update_time": "1555490295040",
        "name": "小明"
    }})
}

#[post("/")]
async fn finduser() -> Value {
    json!({
        "status": 0,
        "data": {
            "id": 1,
            "account": "admin",
            "name": "aa",
            "sex": "男",
            "age": 18,
            "phone": "123456789",
            "email": "action@action.cn"
        }
    })
}

#[post("/")]
async fn deleteusers() -> Value {
    json!({
        "status": 0
    })
}

#[post("/")]
async fn finduserlist() -> Value {
    json!({
        "status": 0,
    "data": [
        {
            "id": 1,
            "account": "admin",
            "name": "aa",
            "sex": "男",
            "age": 18,
            "phone": "123456789",
            "email": "action@action.cn"
        },
        {
            "id": 2,
            "account": "admin01",
            "name": null,
            "sex": "女",
            "age": 0,
            "phone": "123456789",
            "email": "action@action.cn"
        },
        {
            "id": 3,
            "account": "action01",
            "name": null,
            "sex": "男",
            "age": 0,
            "phone": "1234567890",
            "email": "action01@action.cn"
        },
        {
            "id": 4,
            "account": "action02",
            "name": null,
            "sex": "女",
            "age": 0,
            "phone": "123456789",
            "email": "action02@action.cn"
        },
        {
            "id": 5,
            "account": "admin11",
            "name": "d",
            "sex": "男",
            "age": 212,
            "phone": "1",
            "email": "11"
        },
        {
            "id": 8,
            "account": "admin1234",
            "name": null,
            "sex": "男",
            "age": 20,
            "phone": "1374648484",
            "email": "56448@qq.com"
        },
        {
            "id": 9,
            "account": "admin123456",
            "name": null,
            "sex": "男",
            "age": 20,
            "phone": "17853530111",
            "email": "action005@qq.com"
        }
    ]
    })
}

#[post("/")]
async fn login() -> Value {
    json!({
    "status": 0,
    "msg": "登陆成功",
    "data": {
        "id": 2,
        "account": "admin01",
        "password": "",
        "email": "action@action.cn",
        "age": 0,
        "sex": 0,
        "del": 0,
        "phone": "123456789",
        "question": "这是什么问题？",
        "asw": "不知道",
        "role": 2,
        "create_time": "1517930634000",
        "update_time": "1518100629000",
        "name": null
    }
    })
}

#[get("/")]
async fn findorders_nopages() -> Value {
    json!({
    "status": 0,
    "data": [
        {
            "orderNo": "1519095514229",
            "amount": 863.56,
            "type": 1,
            "typeDesc": "在线支付",
            "freight": 0,
            "status": 3,
            "statusDesc": "已发货",
            "paymentTime": "",
            "deliveryTime": "2018-02-20 21:37:05",
            "finishTime": "",
            "closeTime": "",
            "created": "2018-02-20 10:58:34",
            "orderItems": [
                {
                    "orderNo": "1519095514229",
                    "goodsId": 6,
                    "goodsName": "锂基脂 00#",
                    "iconUrl": "[地址1",
                    "curPrice": 215.89,
                    "quantity": 2,
                    "totalPrice": 431.78,
                    "created": null
                },
                {
                    "orderNo": "1519095514229",
                    "goodsId": 7,
                    "goodsName": "锂基脂 00#",
                    "iconUrl": "[地址1",
                    "curPrice": 215.89,
                    "quantity": 2,
                    "totalPrice": 431.78,
                    "created": null
                }
            ],
            "addrId": 4,
            "deliveryName": "李四",
            "address": null
        }
    ]
    })
}

#[get("/")]
async fn search() -> Value {
    json!({
        "status": 0,
    "data": {
        "pageNum": 1,
        "pageSize": 10,
        "totalRecord": 1,
        "totalPage": 1,
        "startIndex": 0,
        "data": [
            {
                "orderNo": "1519095514229",
                "amount": 863.56,
                "type": 1,
                "typeDesc": "在线支付",
                "freight": 0,
                "status": 3,
                "statusDesc": "已发货",
                "paymentTime": "",
                "deliveryTime": "2018-02-20 21:37:05",
                "finishTime": "",
                "closeTime": "",
                "created": "2018-02-20 10:58:34",
                "orderItems": [
                    {
                        "orderNo": "1519095514229",
                        "goodsId": 6,
                        "goodsName": "锂基脂 00#",
                        "iconUrl": "[地址1",
                        "curPrice": 215.89,
                        "quantity": 2,
                        "totalPrice": 431.78,
                        "created": null
                    },
                    {
                        "orderNo": "1519095514229",
                        "goodsId": 7,
                        "goodsName": "锂基脂 00#",
                        "iconUrl": "[地址1",
                        "curPrice": 215.89,
                        "quantity": 2,
                        "totalPrice": 431.78,
                        "created": null
                    }
                ],
                "addrId": 4,
                "deliveryName": "李四",
                "address": {
                    "name": "李四",
                    "phone": null,
                    "mobile": "123456789",
                    "province": "山东省",
                    "city": "烟台市",
                    "district": "芝罘区",
                    "addr": "魁玉路100号",
                    "zip": "264000"
                }
            }
        ],
        "prePage": 1,
        "nextPage": 1
    }
    })
}

#[post("/")]
async fn findorders() -> Value {
    json!({
        "status": 0,
    "data": {
        "pageNum": 1,
        "pageSize": 10,
        "totalRecord": 21,
        "totalPage": 3,
        "startIndex": 0,
        "data": [
            {
                "orderNo": "1519095514229",
                "amount": 863.56,
                "type": 1,
                "typeDesc": "在线支付",
                "freight": 0,
                "status": 3,
                "statusDesc": "已发货",
                "paymentTime": "",
                "deliveryTime": "2018-02-20 21:37:05",
                "finishTime": "",
                "closeTime": "",
                "created": "2018-02-20 10:58:34",
                "orderItems": [
                    {
                        "orderNo": "1519095514229",
                        "goodsId": 6,
                        "goodsName": "锂基脂 00#",
                        "iconUrl": "[地址1",
                        "curPrice": 215.89,
                        "quantity": 2,
                        "totalPrice": 431.78,
                        "created": null
                    },
                    {
                        "orderNo": "1519095514229",
                        "goodsId": 7,
                        "goodsName": "锂基脂 00#",
                        "iconUrl": "[地址1",
                        "curPrice": 215.89,
                        "quantity": 2,
                        "totalPrice": 431.78,
                        "created": null
                    }
                ],
                "addrId": 4,
                "deliveryName": "李四",
                "address": null
            }
        ],
        "prePage": 1,
        "nextPage": 1
    }
    })
}

#[get("/")]
async fn getdetail() -> Value {
    json!({
        "status": 0,
    "data": {
        "orderNo": "1519095514229",
        "amount": 863.56,
        "type": 1,
        "typeDesc": "在线支付",
        "freight": 0,
        "status": 3,
        "statusDesc": "已发货",
        "paymentTime": "",
        "deliveryTime": "2018-02-20 21:37:05",
        "finishTime": "",
        "closeTime": "",
        "created": "2018-02-20 10:58:34",
        "orderItems": [
            {
                "orderNo": "1519095514229",
                "goodsId": 6,
                "goodsName": "锂基脂 00#",
                "iconUrl": "[地址1",
                "curPrice": 215.89,
                "quantity": 2,
                "totalPrice": 431.78,
                "created": null
            },
            {
                "orderNo": "1519095514229",
                "goodsId": 7,
                "goodsName": "锂基脂 00#",
                "iconUrl": "[地址1",
                "curPrice": 215.89,
                "quantity": 2,
                "totalPrice": 431.78,
                "created": null
            }
        ],
        "addrId": 4,
        "deliveryName": "李四",
        "address": {
            "name": "李四",
            "phone": null,
            "mobile": "123456789",
            "province": "山东省",
            "city": "烟台市",
            "district": "芝罘区",
            "addr": "魁玉路100号",
            "zip": "264000"
        }
    }
    })
}

#[post("/")]
async fn delparam() -> Value {
    json!({
        "status": 0
    })
}

#[get("/")]
async fn findpathparam() -> Value {
    json!({
        "status": 0,
        "data": [
            {
                "id": 10038,
                "name": "管阀类"
            },
            {
                "id": 10039,
                "name": "易损类"
            }
        ]
    })
}

#[get("/")]
async fn findptype() -> Value {
    json!({
        "status": 0,
    "data": [
        {
            "id": 10023,
            "parent_id": 0,
            "name": "混凝土机械",
            "status": true,
            "sort_order": 1,
            "level": 0,
            "created": "1518421458000",
            "updated": "1518421458000",
            "children": null
        },
        {
            "id": 10070,
            "parent_id": 0,
            "name": "部件类型01",
            "status": true,
            "sort_order": 1,
            "level": 0,
            "created": "1554713093000",
            "updated": "1554713352000",
            "children": null
        },
        {
            "id": 10024,
            "parent_id": 0,
            "name": "建筑起重机械",
            "status": true,
            "sort_order": 2,
            "level": 0,
            "created": "1518421473000",
            "updated": "1518421473000",
            "children": null
        },
        {
            "id": 10025,
            "parent_id": 0,
            "name": "路面机械",
            "status": true,
            "sort_order": 3,
            "level": 0,
            "created": "1518421483000",
            "updated": "1518421483000",
            "children": null
        },
        {
            "id": 10026,
            "parent_id": 0,
            "name": "土方机械",
            "status": true,
            "sort_order": 4,
            "level": 0,
            "created": "1518421493000",
            "updated": "1518421493000",
            "children": null
        },
        {
            "id": 10027,
            "parent_id": 0,
            "name": "环卫机械",
            "status": true,
            "sort_order": 5,
            "level": 0,
            "created": "1518421502000",
            "updated": "1518421502000",
            "children": null
        },
        {
            "id": 10028,
            "parent_id": 0,
            "name": "工业车辆",
            "status": true,
            "sort_order": 6,
            "level": 0,
            "created": "1518421513000",
            "updated": "1518421513000",
            "children": null
        },
        {
            "id": 10029,
            "parent_id": 0,
            "name": "模型专区",
            "status": true,
            "sort_order": 7,
            "level": 0,
            "created": "1518421522000",
            "updated": "1518421522000",
            "children": null
        },
        {
            "id": 10030,
            "parent_id": 0,
            "name": "特辑专区",
            "status": true,
            "sort_order": 8,
            "level": 0,
            "created": "1518421536000",
            "updated": "1518421536000",
            "children": null
        },
        {
            "id": 10031,
            "parent_id": 0,
            "name": "运费-01",
            "status": true,
            "sort_order": 9,
            "level": 0,
            "created": "1518421543000",
            "updated": "1518421932000",
            "children": null
        }
    ]
    })
}

#[post("/")]
async fn findchildren() -> Value {
    json!({
        "status": 0,
    "data": [
        {
            "id": 10064,
            "parent_id": 10023,
            "name": "电器元件",
            "status": true,
            "sort_order": 0,
            "level": 1,
            "created": "1551679846000",
            "updated": "1551679846000",
            "children": null
        },
        {
            "id": 10032,
            "parent_id": 10023,
            "name": "泵送搅拌系统",
            "status": true,
            "sort_order": 1,
            "level": 1,
            "created": "1518421618000",
            "updated": "1518421618000",
            "children": null
        },
        {
            "id": 10033,
            "parent_id": 10023,
            "name": "油品",
            "status": true,
            "sort_order": 2,
            "level": 1,
            "created": "1518421628000",
            "updated": "1518421628000",
            "children": null
        },
        {
            "id": 10034,
            "parent_id": 10023,
            "name": "电器元件",
            "status": true,
            "sort_order": 3,
            "level": 1,
            "created": "1518421637000",
            "updated": "1518421637000",
            "children": null
        },
        {
            "id": 10035,
            "parent_id": 10023,
            "name": "地盘配件",
            "status": true,
            "sort_order": 4,
            "level": 1,
            "created": "1518421648000",
            "updated": "1518421648000",
            "children": null
        },
        {
            "id": 10036,
            "parent_id": 10023,
            "name": "发动机件",
            "status": true,
            "sort_order": 5,
            "level": 1,
            "created": "1518421662000",
            "updated": "1518421662000",
            "children": null
        },
        {
            "id": 10037,
            "parent_id": 10023,
            "name": "轮胎",
            "status": true,
            "sort_order": 6,
            "level": 1,
            "created": "1518421670000",
            "updated": "1518421670000",
            "children": null
        }
    ]
    })
}

#[post("/")]
async fn updateparam() -> Value {
    json!({
        "status": 0,
        "msg": "产品参数修改成功！"
    })
}

#[post("/")]
async fn saveparam() -> Value {
    json!({
        "status": 0,
        "msg": "产品参数新增成功！"
    })
}

#[post("/")]
async fn pic_upload() -> Value {
    json!({
        "status": 200
    })
}

#[post("/")]
async fn productlist() -> Value {
    json!({
        "status": 0,
    "data": [
        {
            "id": 6,
            "name": "锂基脂 00#",
            "price": 215.89,
            "status": 2,
            "statusDesc": "上架",
            "productCategory": "混凝土机械",
            "partsCategory": "锂基油"
        },
        {
            "id": 7,
            "name": "锂基脂 00#",
            "price": 215.89,
            "status": 2,
            "statusDesc": "上架",
            "productCategory": "混凝土机械",
            "partsCategory": "锂基油"
        },
        {
            "id": 8,
            "name": "道达尔液压油200L",
            "price": 215.89,
            "status": 2,
            "statusDesc": "上架",
            "productCategory": "混凝土机械",
            "partsCategory": "锂基油"
        }
    ]
    })
}

#[post("/")]
async fn upload() -> Value {
    json!({
        "status": 0,
        "data": {
            "url": "/upload/b63bf219-5f9f-4019-998f-7497c0d25227.jpg"
        }
    })
}

#[post("/")]
async fn searchproducts() -> Value {
    json!({
        "status": 0,
    "data": {
        "pageNum": 1,
        "pageSize": 10,
        "totalRecord": 3,
        "totalPage": 1,
        "startIndex": 0,
        "data": [
            {
                "id": 6,
                "name": "锂基脂 00#",
                "price": 215.89,
                "status": 2,
                "statusDesc": "在售",
                "productCategory": "混凝土机械",
                "partsCategory": "锂基油",
                "iconUrl": "/upload/10a42221-06a8-4001-8cc6-b2deb3b9e964.png",
                "hot": 1,
                "hotStatus": "热销"
            },
            {
                "id": 7,
                "name": "锂基脂 00#",
                "price": 215.89,
                "status": 2,
                "statusDesc": "在售",
                "productCategory": "混凝土机械",
                "partsCategory": "锂基油",
                "iconUrl": "",
                "hot": 1,
                "hotStatus": "热销"
            },
            {
                "id": 39,
                "name": "锂基脂 00#",
                "price": 215.89,
                "status": 1,
                "statusDesc": "待售",
                "productCategory": "混凝土机械",
                "partsCategory": "锂基油",
                "iconUrl": "/upload/f6703a22-c79a-4ec1-85b2-7d1652af78f3.png",
                "hot": 2,
                "hotStatus": "一般"
            }
        ],
        "prePage": 1,
        "nextPage": 1
    }
    })
}

#[post("/")]
async fn getdetail_2() -> Value {
    json!({
        "status": 0,
        "data": {
            "id": 6,
            "name": "锂基脂 00#",
            "productId": 10023,
            "partsId": 10044,
            "iconUrl": "/upload/10a42221-06a8-4001-8cc6-b2deb3b9e964.png",
            "subImages": "/upload/10a42221-06a8-4001-8cc6-b2deb3b9e964.png,/upload/6308150b-1afd-41e7-acfa-45627015aa37.png",
            "detail": "
    商品详情

    ",
            "specParam": "规格参数",
            "price": 215.89,
            "stock": 89,
            "status": 2,
            "hot": 1,
            "created": "1519095352000",
            "updated": "1554715583000"
        }
    })
}

#[post("/")]
async fn setstatus() -> Value {
    json!({
        "status": 0,
        "msg": "修改产品状态成功！"
    })
}

#[post("/")]
async fn saveproduct() -> Value {
    json!({
        "status": 0,
        "msg": "产品新增成功！"
    })
}

#[get("/")]
async fn findallparams() -> Value {
    json!({
        "status": 0,
        "data": {
            "xxx": "太长了放不下，真tm多焯"
        }
    })        
}

#[get("/")]
async fn getcartcount() -> Value {
    json!({
        "status": 0,
        "data": 3
    })
}

#[get("/")]
async fn updatecarts() -> Value {
    json!({
         "status": 0,
        "data": {
            "lists": [
                {
                    "id": 18,
                    "userId": 1,
                    "productId": 6,
                    "name": "锂基脂 00#",
                    "quantity": 7,
                    "price": 215.89,
                    "status": 2,
                    "totalPrice": 1511.23,
                    "stock": 89,
                    "iconUrl": "/upload/10a42221-06a8-4001-8cc6-b2deb3b9e964.png",
                    "checked": 0
                }
            ],
            "totalPrice": 0
        }
    })
}

#[get("/")]
async fn clearcarts() -> Value {
    json!({
        "status": 0,
        "msg": "成功清空购物车！"
    })
}

#[post("/")]
async fn delcarts() -> Value {
    json!({
        "status": 0,
        "data": {
            "lists": [
                {
                    "id": 16,
                    "userId": 1,
                    "productId": 29,
                    "name": "压路机",
                    "quantity": 1,
                    "price": 11,
                    "status": 2,
                    "totalPrice": 11,
                    "stock": 100,
                    "iconUrl": "",
                    "checked": 0
                }
            ],
            "totalPrice": 0
        }
    })
}

#[get("/")]
async fn findallcarts() -> Value {
    json!({
        "status": 0,
    "data": {
        "lists": [
            {
                "id": 16,
                "userId": 1,
                "productId": 29,
                "name": "压路机",
                "quantity": 1,
                "price": 11,
                "status": 2,
                "totalPrice": 11,
                "stock": 100,
                "iconUrl": "",
                "checked": 0
            },
            {
                "id": 17,
                "userId": 1,
                "productId": 6,
                "name": "锂基脂 00#",
                "quantity": 2,
                "price": 215.89,
                "status": 2,
                "totalPrice": 431.78,
                "stock": 89,
                "iconUrl": "/upload/10a42221-06a8-4001-8cc6-b2deb3b9e964.png",
                "checked": 0
            }
        ],
        "totalPrice": 0
    }
    })
}

#[post("/")]
async fn savecart() -> Value {
    json!({
        "status": 0,
        "msg": "商品已成功加入购物车！"
    })
}

#[post("/")]
async fn findAddressById() -> Value {
    json!({
        "status": 0,
    "data": {
        "id": 5,
        "uid": 3,
        "name": "张三",
        "phone": null,
        "mobile": "123456789",
        "province": "山东省",
        "city": "烟台市",
        "district": "芝罘区",
        "addr": "魁玉路100号",
        "zip": "264000",
        "default_addr": 0,
        "del_state": 0,
        "created": "1519038756000",
        "updated": "1519038756000"
    }
    })
}

#[get("/")]
async fn setdefault() -> Value {
    json!({
        "status": 0,
    "data": [
        {
            "id": 29,
            "uid": 1,
            "name": "阿三",
            "phone": null,
            "mobile": "15641515",
            "province": "山东",
            "city": "烟台\n",
            "district": null,
            "addr": null,
            "zip": null,
            "default_addr": 1,
            "del_state": 0,
            "created": "1554361791000",
            "updated": "1554773227000"
        },
        {
            "id": 23,
            "uid": 1,
            "name": "啊实打实",
            "phone": null,
            "mobile": "11111111111",
            "province": "河北省",
            "city": "唐山市",
            "district": "路北区",
            "addr": "打算撒大声地",
            "zip": "261300",
            "default_addr": 0,
            "del_state": 0,
            "created": "1552638101000",
            "updated": "1554368779000"
        }
    ]
    })
}

#[get("/")]
async fn findaddrs() -> Value {
    json!({
        "status": 0,
    "data": [
        {
            "id": 4,
            "uid": 3,
            "name": "李四",
            "phone": null,
            "mobile": "123456789",
            "province": "山东省",
            "city": "烟台市",
            "district": "芝罘区",
            "addr": "魁玉路100号",
            "zip": "264000",
            "dfault": false,
            "created": "1519038748000",
            "updated": "1519039374000"
        },
        {
            "id": 5,
            "uid": 3,
            "name": "张三",
            "phone": null,
            "mobile": "123456789",
            "province": "山东省",
            "city": "烟台市",
            "district": "芝罘区",
            "addr": "魁玉路100号",
            "zip": "264000",
            "dfault": false,
            "created": "1519038756000",
            "updated": "1519038756000"
        }
    ]
    })
}

#[get("/")]
async fn deladdr() -> Value {
    json!({
        "status": 0,
        "data": {
            "xxxx": "太长了返回不了"
        }
    })
}

#[post("/")]
async fn saveaddr() -> Value {
    json!({
        "status": 0,
        "data": {
            "xxxx": "太长了返回不了"
        }
    })
}

#[post("/")]
async fn confirmreceipt() -> Value {
    json!({
        "status": 0,
        "msg": "订单已确认收货！"
    })
}

#[post("/")]
async fn getdetail_3() -> Value {
    json!({
        "status": 0,
        "data": {
            "xxxx": "太长了返回不了"
        }
    })
}

#[post("/")]
async fn cancelorder() -> Value {
    json!({
         "status": 0,
         "msg": "该订单已经取消！"
    })
}

#[get("/")]
async fn getlist() -> Value {
    json!({
            "status": 0,
    "data": {
        "pageNum": 1,
        "pageSize": 10,
        "totalRecord": 15,
        "totalPage": 2,
        "startIndex": 0,
        "data": [
            {
                "orderNo": "1552546163312",
                "amount": 316,
                "type": 1,
                "typeDesc": "在线支付",
                "freight": 0,
                "status": 1,
                "statusDesc": "未付款",
                "paymentTime": "",
                "deliveryTime": "",
                "finishTime": "",
                "closeTime": "",
                "created": "2019-03-14 14:49:23",
                "orderItems": [
                    {
                        "orderNo": "1552546163312",
                        "goodsId": 9,
                        "goodsName": "润滑油",
                        "iconUrl": "/upload/47fb50a7-c9d2-4638-971e-adaa1ddccef2.png",
                        "curPrice": 79,
                        "quantity": 3,
                        "totalPrice": 237,
                        "created": null
                    },
                    {
                        "orderNo": "1552546163312",
                        "goodsId": 10,
                        "goodsName": "润滑油",
                        "iconUrl": "/upload/a688babd-4c45-484a-978d-c746e62618bf.png",
                        "curPrice": 79,
                        "quantity": 1,
                        "totalPrice": 79,
                        "created": null
                    }
                ],
                "addrId": 21,
                "deliveryName": "",
                "address": null
            }
        ],
        "prePage": 1,
        "nextPage": 2
    }
    })
}

#[post("/")]
async fn createorder() -> Value {
    json!({
        "status": 0,
    "data": {
        "orderNo": "1519099875344",
        "amount": 863.56,
        "type": 1,
        "typeDesc": "在线支付",
        "freight": 0,
        "status": 1,
        "statusDesc": "未付款",
        "paymentTime": "",
        "deliveryTime": "",
        "finishTime": "",
        "closeTime": "",
        "created": "2018-02-20 12:11:15",
        "orderItems": [
            {
                "orderNo": "1519099875344",
                "goodsId": 7,
                "goodsName": "锂基脂 00#",
                "iconUrl": "/upload/aa2e590f-c4eb-42e0-8260-54af15ed4760.jpg",
                "curPrice": 215.89,
                "quantity": 2,
                "totalPrice": 431.78,
                "created": null
            },
            {
                "orderNo": "1519099875344",
                "goodsId": 6,
                "goodsName": "锂基脂 00#",
                "iconUrl": "/upload/aa2e590f-c4eb-42e0-8260-54af15ed4760.jpg",
                "curPrice": 215.89,
                "quantity": 2,
                "totalPrice": 431.78,
                "created": null
            }
        ],
        "addrId": 4,
        "deliveryName": "李四",
        "address": {
            "name": "李四",
            "phone": null,
            "mobile": "123456789",
            "province": "山东省",
            "city": "烟台市",
            "district": "芝罘区",
            "addr": "魁玉路100号",
            "zip": "264000"
        }
    }
    })
}

#[post("/")]
async fn getUserByAccount() -> Value {
    json!({
        "status": 0,
    "data": {
        "id": 1,
        "account": "admin",
        "password": "",
        "email": "546415155@qq.com",
        "age": 18,
        "sex": 0,
        "del": 0,
        "phone": "17844545452",
        "question": "1",
        "asw": "",
        "role": 1,
        "create_time": "1517672134000",
        "update_time": "1555653582000",
        "name": "小明000"
    }
    })
}

#[post("/")]
async fn do_logout() -> Value {
    json!({
        "status": 0
    })
}

#[post("/")]
async fn updateuserinfo() -> Value {
    json!({
        "status": 0,
    "msg": "用户信息修改成功！",
    "data": {
        "id": 1,
        "account": "admin",
        "password": "e10adc3949ba59abbe56e057f20f883e",
        "email": "action@action.cn",
        "age": 18,
        "sex": 1,
        "del": 0,
        "phone": "123456789",
        "question": "1",
        "asw": "1",
        "role": 1,
        "create_time": "1517672134000",
        "update_time": "1554775697456",
        "name": null
    }
    })
}

#[post("/")]
async fn updatepassword() -> Value {
    json!({
        "status": 0,
        "msg": "密码修改成功！"
    })
}

#[post("/")]
async fn resetpassword() -> Value {
    json!({
        "status": 0,
        "msg": "密码修改成功！"
    })
}

#[post("/")]
async fn checkuserasw() -> Value {
    json!({
        "status": 0,
        "msg": "23521a83-0ae0-422e-bd8b-4d27ec79d005"
    })
}

#[post("/")]
async fn getuserquestion() -> Value {
    json!({
        "status": 0,
        "data": "你的密码是什么"
    })
}

#[get("/")]
async fn getuserinfo() -> Value {
    json!({
        "status": 0,
        "data": {
            "id": 1,
            "account": "admin",
            "password": "",
            "email": "24564@qq.com",
            "age": 18,
            "sex": 1,
            "del": 0,
            "phone": "184845445",
            "question": "1",
            "asw": "1",
            "role": 1,
            "create_time": "1517672134000",
            "update_time": "1554083330000",
            "name": "阿三"
        }
    })
}

#[post("/")]
async fn do_register() -> Value {
    json!({
        "status": 0,
        "msg": "注册成功！"
    })
}

#[post("/")]
async fn do_login() -> Value {
    json!({
         "status": 0,
    "msg": "登陆成功",
    "data": {
        "id": 1,
        "account": "admin",
        "password": "",
        "email": "24564@qq.com",
        "age": 18,
        "sex": 1,
        "del": 0,
        "phone": "184845445",
        "question": "1",
        "asw": "1",
        "role": 1,
        "create_time": "1517672134000",
        "update_time": "1554083330000",
        "name": "阿三"
    }
    })
}

#[post("/")]
async fn do_check_info() -> Value {
    json!({"status":0,"msg":"信息验证成功！"})
}

#[post("/")]
async fn findfloors() -> Value {
    json!({
        "status": 0,
        "data": {
            "xxx": "太长了写不下"
        }
    })
}

#[post("/")]
async fn findhotproducts() -> Value {
    json!({
        "status": 0,
        "data": {
            "xxx": "太长了写不下"
        }
    })
}

#[post("/")]
async fn getdetail_4() -> Value {
    json!({
        "status": 0,
    "data": {
        "id": 6,
        "name": "锂基脂 00#",
        "productId": 10023,
        "partsId": 10044,
        "iconUrl": "/upload/10a42221-06a8-4001-8cc6-b2deb3b9e964.png",
        "subImages": "/upload/10a42221-06a8-4001-8cc6-b2deb3b9e964.png,/upload/6308150b-1afd-41e7-acfa-45627015aa37.png",
        "detail": "
商品详情

",
        "specParam": "规格参数",
        "price": 215.89,
        "stock": 82,
        "status": 2,
        "hot": 1,
        "created": "1519095352000",
        "updated": "1554773510000"
    }
    })
}

#[post("/")]
async fn findproducts() -> Value {
    json!({
        "status": 0,
    "data": {
        "pageNum": 1,
        "pageSize": 10,
        "totalRecord": 2,
        "totalPage": 1,
        "startIndex": 0,
        "data": [
            {
                "id": 6,
                "name": "锂基脂 00#",
                "price": 215.89,
                "status": 2,
                "statusDesc": "在售",
                "productCategory": "混凝土机械",
                "partsCategory": "锂基油",
                "iconUrl": "/upload/10a42221-06a8-4001-8cc6-b2deb3b9e964.png",
                "hot": 1,
                "hotStatus": "热销"
            },
            {
                "id": 7,
                "name": "锂基脂 00#",
                "price": 215.89,
                "status": 2,
                "statusDesc": "在售",
                "productCategory": "混凝土机械",
                "partsCategory": "锂基油",
                "iconUrl": "",
                "hot": 1,
                "hotStatus": "热销"
            }
        ],
        "prePage": 1,
        "nextPage": 1
    }
    })
}

#[launch]
fn rocket() -> _ {
    rocket::build()
        // get routers
        .mount("/actionmall", routes![index])
        .mount("/actionmall/mgr/user/updateuser.do", routes![updateuser])
        .mount("/actionmall/mgr/user/finduser.do", routes![finduser])
        .mount("/actionmall/mgr/user/deleteusers.do", routes![deleteusers])
        .mount("/actionmall/mgr/user/finduserlist.do", routes![finduserlist])
        .mount("/actionmall/mgr/user/login.do", routes![login])
        .mount("/actionmall/mgr/order/findorders_nopages.do", routes![findorders_nopages])
        .mount("/actionmall/mgr/order/search.do", routes![search])
        .mount("/actionmall/mgr/order/findorders.do", routes![findorders])
        .mount("/actionmall/mgr/order/getdetail.do", routes![getdetail])
        .mount("/actionmall/mgr/param/delparam.do", routes![delparam])
        .mount("/actionmall/mgr/param/findpathparam.do", routes![findpathparam])
        .mount("/actionmall/mgr/param/findptype.do", routes![findptype])
        .mount("/actionmall/mgr/param/findchildren.do", routes![findchildren])
        .mount("/actionmall/mgr/param/updateparam.do", routes![updateparam])
        .mount("/actionmall/mgr/param/saveparam.do", routes![saveparam])
        .mount("/actionmall/mgr/product/pic_upload.do", routes![pic_upload])
        .mount("/actionmall/mgr/product/productlist.do", routes![productlist])
        .mount("/actionmall/mgr/product/upload.do", routes![upload])
        .mount("/actionmall/mgr/product/searchproducts.do", routes![searchproducts])
        .mount("/actionmall/mgr/product/getdetail.do", routes![getdetail_2])
        .mount("/actionmall/mgr/product/setstatus.do", routes![setstatus])
        .mount("/actionmall/mgr/product/saveproduct.do", routes![saveproduct])
        .mount("/actionmall/param/findallparams.do", routes![findallparams])
        .mount("/actionmall/cart/getcartcount.do", routes![getcartcount])
        .mount("/actionmall/cart/updatecarts.do", routes![updatecarts])
        .mount("/actionmall/cart/clearcarts.do", routes![clearcarts])
        .mount("/actionmall/cart/delcarts.do", routes![delcarts])
        .mount("/actionmall/cart/findallcarts.do", routes![findallcarts])
        .mount("/actionmall/cart/savecart.do", routes![savecart])
        .mount("/actionmall/addr/findAddressById.do", routes![findAddressById])
        .mount("/actionmall/addr/setdefault.do", routes![setdefault])
        .mount("/actionmall/addr/findaddrs.do", routes![findaddrs])
        .mount("/actionmall/addr/deladdr.do", routes![deladdr])
        .mount("/actionmall/addr/saveaddr.do", routes![saveaddr])
        .mount("/actionmall/order/confirmreceipt.do", routes![confirmreceipt])
        .mount("/actionmall/order/getdetail.do", routes![getdetail_3])
        .mount("/actionmall/order/cancelorder.do", routes![cancelorder])
        .mount("/actionmall/order/getlist.do", routes![getlist])
        .mount("/actionmall/order/createorder.do", routes![createorder])
        .mount("/actionmall/user/getUserByAccount.do", routes![getUserByAccount])
        .mount("/actionmall/user/do_logout.do", routes![do_logout])
        .mount("/actionmall/user/updateuserinfo.do", routes![updateuserinfo])
        .mount("/actionmall/user/updatepassword.do", routes![updatepassword])
        .mount("/actionmall/user/resetpassword.do", routes![resetpassword])
        .mount("/actionmall/user/checkuserasw.do", routes![checkuserasw])
        .mount("/actionmall/user/getuserquestion.do", routes![getuserquestion])
        .mount("/actionmall/user/getuserinfo.do", routes![getuserinfo])
        .mount("/actionmall/user/do_register.do", routes![do_register])
        .mount("/actionmall/user/do_login.do", routes![do_login])
        .mount("/actionmall/user/do_check_info.do", routes![do_check_info])
        .mount("/actionmall/product/findfloors.do", routes![findfloors])
        .mount("/actionmall/product/findhotproducts.do", routes![findhotproducts])
        .mount("/actionmall/product/getdetail.do", routes![getdetail_4])
        .mount("/actionmall/product/findproducts.do", routes![findproducts])
}
