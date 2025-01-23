package com.snowzen.external.bangumi;

import com.snowzen.external.bangumi.model.BangumiPage;
import com.snowzen.external.bangumi.model.Episode;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * Bangumi 章节 API。
 *
 * @author snow-zen
 * @see <a href="https://bangumi.github.io/api/">Bangumi API</a>
 */
@RegisterRestClient
public interface EpisodeApi {

    /**
     * 获取某一条目下的章节信息。
     *
     * @param subjectId 条目 id。
     * @param type      章节类型。
     * @param limit     分页大小。
     * @param offset    分页偏移量。
     * @return 携带章节数据的 Bangumi 分页。
     * @see <a href="https://bangumi.github.io/api/#/%E7%AB%A0%E8%8A%82/getEpisodes">getEpisodes</a>
     */
    @GET
    @Path("/v0/episodes")
    BangumiPage<Episode> getEpisodes(@QueryParam("subjectId") Integer subjectId,
                                     @QueryParam("type") Integer type,
                                     @QueryParam("limit") int limit,
                                     @QueryParam("offset") int offset);
}
