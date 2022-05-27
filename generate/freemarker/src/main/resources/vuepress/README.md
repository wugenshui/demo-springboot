[[_TOC_]]

## 说明

- 文档基于`VuePress v2`编写，具体使用方法参考 [VuePress2 中文文档](https://v2.vuepress.vuejs.org/zh/)

## 文档书写

### 目录结构

```
.
├── docs
│ ├── .vuepress
│ │ ├── public (资源文件)
│ │ ├── styles (自定义样式)
│ │ └── config.js (配置文件)
│ │
│ ├── guide   (使用指引 文件夹)
│ │ └── ***.md
│ │ └── README.md
│ ├── guide   (使用指引 文件夹)
│ │ └── ***.md
│ │ └── README.md
│
└── package.json
```

### config.js配置项说明

- title：站点的标题
- description：站点的副标题
- navbar：顶部导航栏配置
- sidebar：左侧导航栏配置
  
## 注意事项

部分特殊符号会导致构建丢失页面，如 {{ ，需增加 `` 标记，自动部署后需检查自己编写的`MarkDown`文件展示效果是否完整
