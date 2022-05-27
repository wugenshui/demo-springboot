const { defaultTheme } = require("@vuepress/theme-default");
const { searchPlugin } = require("@vuepress/plugin-search");
const { clipboardPlugin } = require("vuepress-plugin-clipboard");

module.exports = {
  // 站点的标题
  title: "我是标题,我在config.js中修改",
  // 站点的副标题
  description: "我是副标题,我在config.js中修改",
  head: [
    // 自定义 favicon
    ["link", { rel: "icon", href: "/favicon.ico" }],
  ],
  theme: defaultTheme({
    // 顶部导航栏配置
    navbar: [
      {
        text: "我是顶部导航栏",
        link: "/guide/",
      },
    ],
    // 左侧导航栏配置
    sidebar: [
      {
        text: "说明文档",
        link: "/guide/",
        children: ["/guide/foo"],
      },
    ],
    // gitlab仓库地址
    repo: "${repo}",
    repoLabel: "代码库",
    editLink: true,
    editLinkText: "编辑当前页面",
    editLinkPattern: ":repo/-/edit/master/docs/:path",
  }),
  // 插件配置
  plugins: [searchPlugin({}), clipboardPlugin({ align: "top" })],
};
