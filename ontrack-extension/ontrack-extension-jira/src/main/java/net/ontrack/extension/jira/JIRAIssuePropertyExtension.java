package net.ontrack.extension.jira;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import net.ontrack.core.model.Entity;
import net.ontrack.core.security.SecurityRoles;
import net.ontrack.core.support.InputException;
import net.ontrack.extension.api.property.AbstractPropertyExtensionDescriptor;
import net.sf.jstring.Strings;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Locale;
import java.util.regex.Pattern;

@Component
public class JIRAIssuePropertyExtension extends AbstractPropertyExtensionDescriptor {

    private static final Pattern ISSUE_PATTERN = Pattern.compile("[A-Za-z][A-Za-z0-9]*\\-[0-9]+");
    private static final String ISSUE_SEPARATORS = ",; ";

    private final JIRAConfigurationExtension jiraConfigurationExtension;

    @Autowired
    public JIRAIssuePropertyExtension(JIRAConfigurationExtension jiraConfigurationExtension) {
        this.jiraConfigurationExtension = jiraConfigurationExtension;
    }

    @Override
    public EnumSet<Entity> getScope() {
        return EnumSet.of(Entity.VALIDATION_RUN);
    }

    @Override
    public String getExtension() {
        return "jira";
    }

    @Override
    public String getName() {
        return "issue";
    }

    @Override
    public String getDisplayNameKey() {
        return "jira.issue";
    }

    protected String[] parseIssues(String value) {
        return StringUtils.split(value, ISSUE_SEPARATORS);
    }

    @Override
    public void validate(String value) throws InputException {
        String[] issues = parseIssues(value);
        for (String issue : issues) {
            if (!ISSUE_PATTERN.matcher(issue).matches()) {
                throw new JIRAIssuePatternException(issue);
            }
        }
    }

    @Override
    public String toHTML(final Strings strings, final Locale locale, String value) {
        StringBuilder html = new StringBuilder(
                String.format("<span title=\"%s\"><img src=\"extension/%s\" /> ",
                        strings.get(locale, getDisplayNameKey()),
                        "jira.png"));
        // For each issue
        html.append(
                StringUtils.join(
                        Iterables.transform(
                                Arrays.asList(parseIssues(value)),
                                new Function<String, String>() {
                                    @Override
                                    public String apply(String issue) {
                                        return String.format("<a href=\"%s\">%s</a>",
                                                jiraConfigurationExtension.getIssueURL(issue),
                                                StringEscapeUtils.escapeHtml4(issue)
                                        );
                                    }
                                }
                        ),
                        ", "
                )
        );
        // End
        html.append("</span>");
        return html.toString();
    }

    @Override
    public String getRoleForEdition(Entity entity) {
        return SecurityRoles.USER;
    }
}