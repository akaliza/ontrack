package net.ontrack.web.api.model;

import com.google.common.base.Function;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.ontrack.core.model.ProjectSummary;
import net.ontrack.web.api.controller.ProjectController;

import static net.ontrack.web.api.model.ResourceLink.of;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpMethod.DELETE;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectResource extends AbstractResource<ProjectResource> {

    private final int id;
    private final String name;
    private final String description;

    public static Function<ProjectSummary, ProjectResource> summary = new Function<ProjectSummary, ProjectResource>() {
        @Override
        public ProjectResource apply(ProjectSummary o) {
            return new ProjectResource(
                    o.getId(),
                    o.getName(),
                    o.getDescription())
                    .withView("/project/%s", o.getName())
                    .withLink(linkTo(methodOn(ProjectController.class).getProject(o.getName())).withSelfRel())
                    .withLink(of(DELETE, linkTo(methodOn(ProjectController.class).getProject(o.getName())).withRel("deleteProject")))
                    ;
        }
    };

    public static Function<ProjectSummary, ProjectResource> detail = new Function<ProjectSummary, ProjectResource>() {
        @Override
        public ProjectResource apply(ProjectSummary o) {
            return summary
                    .apply(o)
                    // TODO Branches
                    // TODO Actions
                    ;
        }
    };

}
