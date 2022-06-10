import React, { Fragment } from 'react'
import { Button, Form, Input, DatePicker } from "antd";

export default function SetUserData() {
  const onFinish = (values) => {
      console.log("Success:", values);
  };
  
  const onFinishFailed = (errorInfo) => {
      console.log("Failed:", errorInfo);
  };
  return (
    <Fragment>
      <Form
        name="basic"
        labelCol={{
          span: 8,
        }}
        wrapperCol={{
          span: 16,
        }}
        initialValues={{
          remember: true,
        }}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        autoComplete="off"
      >
        <Form.Item
          label="电话号码"
          name="phone"
          rules={[
            {
              required: true,
              message: "请输入新的手机号",
              type: "phone",
            },
          ]}
        >
          <Input type="number" />
        </Form.Item>
        <Form.Item
          name={["user", "email"]}
          label="邮箱"
          rules={[
            {
              required: true,
              message: "请输入格式正确的邮箱",
              type: "email",
            },
          ]}
        >
          <Input style={{ "text-transform": "lowercase" }} />
        </Form.Item>
        <Form.Item
          label="出生日期"
          name="username"
          rules={[
            {
              required: true,
              message: "Please input your username!",
            },
          ]}
        >
          <DatePicker />
        </Form.Item>
        <Form.Item
          wrapperCol={{
            offset: 8,
            span: 16,
          }}
        >
          <Button type="primary" htmlType="submit">
            更新
          </Button>
        </Form.Item>
      </Form>
    </Fragment>
  );
}
