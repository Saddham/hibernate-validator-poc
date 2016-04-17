package com.saddham;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * Created by saddhamp on 8/3/16.
 */

@GroupSequence({Default.class, EmployeeChecks.class, ManagerChecks.class})
public interface OrderedChecks {
}
