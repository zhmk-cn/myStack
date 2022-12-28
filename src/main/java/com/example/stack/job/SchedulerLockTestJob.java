package com.example.stack.job;


import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 需要配合 数据库表
 */
@Component
public class SchedulerLockTestJob {

    private static final int SYSUSERMOST = 5*60*1000;

    private static final int SYSUSERLEAST = 5*60*1000;


    @Scheduled(cron = "0 0/1 * * * ? ")
    @SchedulerLock(name = "SchedulerLockTestJob", lockAtMostFor = SYSUSERMOST, lockAtLeastFor = SYSUSERLEAST)
    //任务名称必须唯一 ，lockAtMostFor最长锁表时间：（防止节点奔溃，不释放锁） lockAtLeastFor 最短锁表     //时间,防止任务重复跑
    @Transactional(rollbackFor = Exception.class)
    public void execute(){
        try {
            Thread.sleep(60*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("定时任务执行了");
    }
}
