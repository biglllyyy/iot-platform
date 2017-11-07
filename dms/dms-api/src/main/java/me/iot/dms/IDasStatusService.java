package me.iot.dms;

import me.iot.dms.entity.DasStatus;

/**
 * Created by sylar on 16/5/25.
 */
public interface IDasStatusService {
    DasStatus getDasStatus(String nodeId);
}
