package net.ontrack.core.model;

import java.util.Arrays;
import java.util.Collection;

/**
 * List of available statuses for the validation runs.
 * <p/>
 * Adding or removing some statuses implies to change as well:
 * <ul>
 * <li><i>general.css</i></li>
 * <li>the <i>status-*.png</i> images</li>
 * <li><i>model.ls</i> for the display names</li>
 * </ul>
 */
public enum Status {

    /**
     * Run passed
     */
    PASSED(),

    /**
     * Run with warning
     */
    WARNING(),
    /**
     * Run should now be fixed
     */
    FIXED(),
    /**
     * Run has shown a defect
     */
    DEFECTIVE(),
    /**
     * Explanation found
     */
    EXPLAINED(FIXED),
    /**
     * Run is under investigation
     */
    INVESTIGATED(DEFECTIVE, EXPLAINED, FIXED),
    /**
     * Run was interrupted
     */
    INTERRUPTED(INVESTIGATED, FIXED),
    /**
     * Run has failed
     */
    FAILED(INTERRUPTED, INVESTIGATED, EXPLAINED, DEFECTIVE);
    /**
     * List of available next states
     */
    private final Collection<Status> next;

    private Status(Status... next) {
        this.next = Arrays.asList(next);
    }

    public Collection<Status> getNext() {
        return next;
    }
}
