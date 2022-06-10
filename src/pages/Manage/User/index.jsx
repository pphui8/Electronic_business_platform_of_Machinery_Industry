import React, { useEffect } from 'react'
import { Divider, List, Typography } from "antd";

export default function User() {

  const [user, setUser] = React.useState([]);

  const getUserData = () => {
    const userData = localStorage.getItem(`user`);
    if (userData) {
      let vec = [];
      vec.push(JSON.parse(userData));
      setUser(vec);
    }
  }

  useEffect(() => {
    getUserData();
  }, []);
  
  return (
    <div>
      <List
        header={<div>用户信息</div>}
        bordered
        dataSource={user}
        renderItem={(item, index) => (
          <List.Item>
            <p>id: {index + 1}</p>
            <p>用户编号：{item.account}</p>
            <p>邮箱{item.email}</p>
          </List.Item>
        )}
      />
    </div>
  );
}
