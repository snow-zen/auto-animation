package com.snowzen.meta;

import com.snowzen.common.ApiResponseStatus;
import com.snowzen.common.ImageAssert;
import com.snowzen.common.enums.MetaInfoAdditionalField;
import com.snowzen.common.enums.MetaPlatform;
import com.snowzen.common.enums.ResourceType;
import com.snowzen.common.exception.ApiException;
import com.snowzen.common.util.ImageUtil;
import com.snowzen.external.bangumi.EpisodeApi;
import com.snowzen.external.bangumi.SubjectApi;
import com.snowzen.external.bangumi.model.BangumiPage;
import com.snowzen.external.bangumi.model.Episode;
import com.snowzen.external.bangumi.model.EpisodeType;
import com.snowzen.external.bangumi.model.ImageData;
import com.snowzen.external.bangumi.model.Subject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 * 基于 Bangumi 平台的元数据信息抓取器。
 *
 * @author snow-zen
 */
@Slf4j
@ApplicationScoped
public class BangumiMetaInfoFetcher implements MetaInfoFetcher {

    private static final int DEFAULT_LIMIT_OF_EPISODE_PAGE = 50;

    @Inject
    @RestClient
    EpisodeApi episodeApi;

    @Inject
    @RestClient
    SubjectApi subjectApi;

    @Override
    public Optional<MetaInfoTreeNode> fetch(String target) {
        int targetId = Integer.parseInt(target);

        // 抓取条目
        Subject subject = subjectApi.getSubject(targetId);
        if (subject == null || subject.getAirDate() == null) {
            log.error("番剧未找到或未确定放送时间，抓取失败");
            return Optional.empty();
        }
        MetaInfoTreeNode root = convertToMetaInfoTreeNode(subject);

        // 抓取条目下所有章节
        getAllEpisodes(subject.getId()).stream()
            .map(this::convertToMetaInfoTreeNode)
            .forEach(root::addChild);

        return Optional.of(root);
    }

    /**
     * 转换为元数据信息树节点。
     */
    private MetaInfoTreeNode convertToMetaInfoTreeNode(Episode episode) {
        MetaInfo data = new MetaInfo();
        data.setOriginPlatform(MetaPlatform.BANGUMI);
        data.setOriginId(String.valueOf(episode.getId()));
        data.setName(episode.getName());
        data.setNameCn(episode.getNameCn());
        data.setSummary(episode.getDescription());
        data.setAirDate(episode.getAirDate());

        Map<String, Object> additionalData = new HashMap<>();
        additionalData.put(
            MetaInfoAdditionalField.EPISODE_TYPE.getKey(),
            mappingResourceType(episode.getType())
        );
        additionalData.put(
            MetaInfoAdditionalField.EPISODE_MAIN_CONTENT_SORT.getKey(),
            episode.getEp()
        );

        data.setAdditionalData(additionalData);
        return new MetaInfoTreeNode(data);
    }

    /**
     * 转换为元数据信息树节点。
     */
    private MetaInfoTreeNode convertToMetaInfoTreeNode(Subject subject) {
        MetaInfo data = new MetaInfo();
        data.setOriginPlatform(MetaPlatform.BANGUMI);
        data.setOriginId(String.valueOf(subject.getId()));
        data.setName(subject.getName());
        data.setNameCn(subject.getNameCn());
        data.setSummary(subject.getSummary());
        data.setAirDate(subject.getAirDate());

        Map<String, Object> additionalData = new HashMap<>();
        additionalData.put(
            MetaInfoAdditionalField.ANIMATION_COVER.getKey(),
            convertImage(subject.getImages())
        );
        additionalData.put(
            MetaInfoAdditionalField.ANIMATION_TOTAL_EPISODES.getKey(),
            subject.getTotalEpisodes()
        );

        data.setAdditionalData(additionalData);
        return new MetaInfoTreeNode(data);
    }

    private ResourceType mappingResourceType(EpisodeType episodeType) {
        if (Objects.requireNonNull(episodeType) == EpisodeType.MAIN) {
            return ResourceType.MAIN_CONTENT;
        }
        throw new ApiException("不支持映射的类型：" + episodeType, ApiResponseStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 转换图片相关类信息。
     */
    private List<ImageAssert> convertImage(ImageData images) {
        List<ImageAssert> imageAsserts = new ArrayList<>();

        ImageUtil.fetchImageInfo(images.getLarge()).ifPresent(imageAsserts::add);
        ImageUtil.fetchImageInfo(images.getCommon()).ifPresent(imageAsserts::add);
        ImageUtil.fetchImageInfo(images.getMedium()).ifPresent(imageAsserts::add);
        ImageUtil.fetchImageInfo(images.getSmall()).ifPresent(imageAsserts::add);
        ImageUtil.fetchImageInfo(images.getGrid()).ifPresent(imageAsserts::add);

        return imageAsserts;
    }

    @Override
    public boolean support(MetaPlatform platform) {
        return platform == MetaPlatform.BANGUMI;
    }

    /**
     * 获取指定条目所有集。
     */
    private List<Episode> getAllEpisodes(Integer subjectId) {
        int offset = 0;
        List<Episode> result = new ArrayList<>();

        do {
            BangumiPage<Episode> bangumiPage = episodeApi.getEpisodes(
                subjectId, EpisodeType.MAIN.getValue(), DEFAULT_LIMIT_OF_EPISODE_PAGE, offset);
            if (bangumiPage.getTotal() == 0) {
                break;
            }

            result.addAll(bangumiPage.getData());
            offset += bangumiPage.getData().size();
            if (offset >= bangumiPage.getTotal()) {
                break;
            }
        } while (true);
        return result;
    }
}
