package com.snowzen.external.bangumi;

import com.snowzen.external.bangumi.model.Subject;
import com.snowzen.external.bangumi.model.SubjectRelation;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import java.util.List;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * Bangumi 条目 API。
 *
 * @author snow-zen、
 * @see <a href="https://bangumi.github.io/api/">Bangumi API</a>
 */
@RegisterRestClient(configKey = "bangumi-api")
@Path("/v0/subjects/{subjectId}")
public interface SubjectApi {

    /**
     * 获取条目。
     *
     * @param subjectId 条目 id。
     * @see <a href="https://bangumi.github.io/api/#/%E6%9D%A1%E7%9B%AE/getSubjectById">getSubjectById</a>
     */
    @GET
    Subject getSubject(@PathParam("subjectId") int subjectId);

    /**
     * 获取关联条目。
     *
     * @param subjectId 源条目 id。
     * @return 关联条目列表。
     * @see <a href="https://bangumi.github.io/api/#/%E6%9D%A1%E7%9B%AE/getRelatedSubjectsBySubjectId">getRelatedSubjectsBySubjectId</a>
     */
    @GET
    @Path("/subjects")
    List<SubjectRelation> getSubjectRelation(@PathParam("subjectId") int subjectId);

}
