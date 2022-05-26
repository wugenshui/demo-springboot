[[_TOC_]]

## 文档地址

[文档地址 http://192.168.0.51:8892](http://192.168.0.51:8892)

说明：本文档基于`VuePress`编写，具体使用方法参考 [VuePress 中文文档](https://www.vuepress.cn/)

## 文档书写

### 目录结构

```
.
├── docs
│ ├── .vuepress
│ │ ├── public (资源文件)
│ │ ├── config.js (配置文件)
│ │
│ ├── guide   (使用指引 文件夹)
│ │ └── ***.md
│ │ └── README.md
│ ├── bug   (问题 文件夹)
│ │ └── ***.md
│ │ └── README.md
│
└── package.json
```

在下方的三个文件夹中新增`MarkDown`文件,然后在`confif.js`中`sidebar`节点的`childrens`数组中增加文件地址配置即可。

```js
    sidebar: [
      // 左侧导航栏配置
      {
        title: '使用指引',
        path: '/guide/',
        collapsable: false,
        sidebarDepth: 2,
        children: ['/guide/fastdfs', '/guide/netty']
      }
    ]
```


## 注意事项

部分特殊符号会导致构建丢失页面，如 {{ ，需增加 `` 标记，自动部署后需检查自己编写的`MarkDown`文件展示效果是否完整
