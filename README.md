# auto-animation

- [ ] 抓取 Bangumi 数据构建 NFO 文件结构对象，对象之间建立 Tree 关系。
- [ ] 抓取 RSS 订阅信息解析出每集的磁力链接。
- [ ] 客户端整理出完整的清单信息并提交。
- [ ] 下载相关资源

目录结构需要能够适配 Emby，大致想法如下：

```
动画名称，建议使用原名（年份）
|- 季度名称，建议使用【Season 1、Season 2，以此类推】
    |- 集名称，建议使用【集标题 - S01E01 - 显示信息.mkv】格式，没有集标题就用动画名称代替
    |- 季对应的 Nfo 文件，固定为【season.nfo】
|- 动画对应的 Nfo 文件，固定为【tvshow.nfo】
```

> 注意：这里只考虑了 TV 动画，剧场版暂时不在考虑范围。且海报、封面、Logo、Banner 之类的
> 命名暂时使用 Emby 的格式，详情请参考：https://emby.media/support/articles/TV-Naming.html#series--season-images

## 开发环境启动

```bash
mvn quarkus:dev
```

启动后，可通过 `http://localhost:8080/q/dev` 访问开发 UI 页面。
