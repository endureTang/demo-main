//package com.service.activitiService;
//
//import org.activiti.engine.*;
//import org.activiti.engine.history.HistoricProcessInstance;
//import org.activiti.engine.history.HistoricVariableInstance;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.activiti.engine.task.Task;
//import org.activiti.spring.integration.Activiti;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.*;
//
// /**
//   RepositoryService:  流程仓库Service，用于管理流程仓库，例如：部署，删除，读取流程资源
//
//   IdentityService：身份Service，可以管理，查询用户，组之间的关系
//
//   RuntimeService：运行时Service，可以处理所有正在运行状态的流程实例，任务等
//
//   TaskService：任务Service，用于管理，查询任务，例如：签收，办理，指派等
//
//   HistoryService：历史Service，可以查询所有历史数据，例如：流程实例，任务，活动，变量，附件等
//
//   FormService：表单Service，用于读取和流程，任务相关的表单数据
//
//   ManagementService：引擎管理Service，和具体业务无关，主要是可以查询引擎配置，数据库，作业等
//
//   DynamicBpmnService：一个新增的服务，用于动态修改流程中的一些参数信息等，是引擎中的一个辅助的服务
//  */
//  @Service
//  public class ActivitiServiceImpl {
//  private Logger logger = LoggerFactory.getLogger(ActivitiServiceImpl.class);
//   //所运行工作流的名字
//   //private static final String PROCESS_DEFINE_KEY = "test.bpmn";
//   private static final String PROCESS_DEFINE_KEY = "demo2";
//   private static final String NEXT_ASSIGNEE = "huangxu2";
//   @Resource
//   private RuntimeService runtimeService;
//   @Resource
//   private IdentityService identityService;
//   @Resource
//   private TaskService taskService;
//   @Resource
//   private HistoryService historyService;
//   @Resource
//   private RepositoryService repositoryService;
//
//    /* //工作流运行服务
//    @Autowired
//    private RuntimeService runtimeService;
//
//    //工作流唯一服务
//    @Autowired
//    private IdentityService identityService;
//
//    //工作流任务服务
//    @Autowired
//    private TaskService taskService;
//
//    //工作流管理服务
//    @Autowired
//    private HistoryService historyService;
//    @Autowired
//    private RepositoryService repositoryService;
//      */
//    /**
//     * 开始流程
//     * @param vac
//     * @param userName
//     * @return
//     */
//
//    public Boolean startActiviti(Activiti vac, String userName) {
//        logger.info("method startActivityDemo begin....");
//        /*认证用户的作用是设置流程发起人：在流程开始之前设置，会自动在表ACT_HI_PROCINST 中的START_USER_ID_中设置用户ID
//        用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中*/
//        try{
//            identityService.setAuthenticatedUserId(userName);
//            // 开始流程
//            ProcessInstance pi = runtimeService.startProcessInstanceByKey(PROCESS_DEFINE_KEY);
//            String processId = pi.getId();
//            logger.info("===============processId==================="+processId);
//
//            // 查询当前任务
//            Task currentTask = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
//            String taskId1 = currentTask.getId();
//            logger.info("===============taskId1==================="+taskId1);
//
//            // 申明任务人
//            //taskService.claim(currentTask.getId(), userName);
//            taskService.setAssignee(taskId1, userName);
//            Map<String, Object> vars = new HashMap<>(4);
//            vars.put("applyUser", userName);
//            vars.put("days", vac.getDays());
//            vars.put("reason", vac.getReason());
//            //在此方法中，Vaction 是申请时的具体信息，在完成“申请请假”任务时，可以将这些信息设置成参数。
//            //完成第一步申请
//            taskService.complete(currentTask.getId(), vars);
//
//            // 到了下一个任务， 应该在此处指派任务由谁来处理
//            // 重新获取当前任务
//            Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
//            String taskId2 = task.getId();
//            logger.info("===============taskId2==================="+taskId2);
//            taskService.setAssignee(taskId2, NEXT_ASSIGNEE);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return true;
//    }
//
//
//
//    public List<Activiti> myActiviti(String userName) {
//        List<ProcessInstance> instanceList = runtimeService.createProcessInstanceQuery().startedBy(userName).list();
//        List<Activiti> activitisList = new ArrayList<>();
//        for (ProcessInstance instance : instanceList) {
//            Activiti activiti = getActiviti(instance);
//            activitisList.add(activiti);
//        }
//        return activitisList;
//    }
//
//
//    /**
//     * 查询需要自己审批
//     * @param userName
//     * @return
//     */
//
//    public List<ActivitiTask> myApproval(String userName) {
//        userName = "huangxu2";
//        List<Task> taskList = taskService.createTaskQuery().taskAssignee(userName)
//                .orderByTaskCreateTime().desc().list();
//                // 多此一举 taskList中包含了以下内容(用户的任务中包含了所在用户组的任务)
//                //        Group group = identityService.createGroupQuery().groupMember(userName).singleResult();
//                //        List<Task> list = taskService.createTaskQuery().taskCandidateGroup(group.getId()).list();
//                //        taskList.addAll(list);
//        List<ActivitiTask> activitiTaskList = new ArrayList<>();
//        for (Task task : taskList) {
//            ActivitiTask activitiTask = new ActivitiTask();
//            activitiTask.setId(task.getId());
//            activitiTask.setName(task.getName());
//            activitiTask.setCreateTime(task.getCreateTime());
//            String instanceId = task.getProcessInstanceId();
//            ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
//            Activiti activiti = getActiviti(instance);
//            activitiTask.setActiviti(activiti);
//            activitiTaskList.add(activitiTask);
//        }
//        return activitiTaskList;
//    }
//
//
//    private Activiti getActiviti(ProcessInstance instance) {
//        Integer days = runtimeService.getVariable(instance.getId(), "days", Integer.class);
//        String reason = runtimeService.getVariable(instance.getId(), "reason", String.class);
//        Activiti activiti = new Activiti();
//        activiti.setApplyUser(instance.getStartUserId());
//        activiti.setDays(days);
//        activiti.setReason(reason);
//        Date startTime = instance.getStartTime(); // activiti 6 才有
//        activiti.setApplyTime(startTime);
//        activiti.setApplyStatus(instance.isEnded() ? "申请结束" : "等待审批");
//        return activiti;
//    }
//
//
//    /**
//     * Activiti任务认领
//     * taskService.setAssignee(String taskId, String userId);
//     * taskService.claim(String taskId, String userId);
//     * taskService.setOwner(String taskId, String userId);
//     * setAssignee和claim两个的区别是在认领任务时，claim会检查该任务是否已经被认领，如果被认领则会抛出ActivitiTaskAlreadyClaimedException ,而setAssignee不会进行这样的检查，其他方面两个方法效果一致。
//     * setOwner和setAssignee的区别在于,setOwner是在代理任务时使用，代表着任务的归属者，而这时，setAssignee代表的是代理办理者，
//     *
//     * 举个例子来说，公司总经理现在有个任务taskA，去核实一下本年度的财务报表，他现在又很忙没时间，于是将该任务委托给其助理进行办理，此时，就应该这么做
//     * taskService.setOwner(taskA.getId(), 总经理.getId());
//     * taskService.setAssignee/claim(taskA.getId(), 助理.getId());
//     */
//    /**
//     * 审批操作
//     * @param userName
//     * @param activitiTask
//     * @return
//     */
//    /**
//     * 同理，result是审批的结果，也是在完成审批任务时需要传入的参数；taskId是刚才老板查询到的当前需要自己完成的审批任务ID。
//     * （如果流程在这里设置分支，可以通过判断result的值来跳转到不同的任务）
//     */
//
//    public Boolean passApproval(String userName, ActivitiTask activitiTask) {
//        userName = "huangxu2";
//        String taskId = activitiTask.getId();
//        String result = activitiTask.getActiviti().getResult();
//        Map<String, Object> vars = new HashMap<>();
//        vars.put("result", result);
//        vars.put("auditor", userName);
//        vars.put("auditTime", new Date());
//        //taskService.claim(taskId, userName);
//        taskService.setAssignee(taskId, userName);
//        taskService.complete(taskId, vars);
//        return true;
//    }
//
//
//    /**
//     * 查询已完成的请假记录
//     * 由于已完成的请假在数据库runtime表中查不到（runtime表只保存正在进行的流程示例信息），所以需要在history表中查询。
//     * @param userName
//     * @return
//     */
//
//    public List<Activiti> myActivitiRecord(String userName) {
//        List<HistoricProcessInstance> hisProInstance = historyService.createHistoricProcessInstanceQuery()
//                .processDefinitionKey(PROCESS_DEFINE_KEY).startedBy(userName).finished()
//                .orderByProcessInstanceEndTime().desc().list();
//
//        List<Activiti> activitiList = new ArrayList<>();
//        for (HistoricProcessInstance hisInstance : hisProInstance) {
//            Activiti activiti = new Activiti();
//            activiti.setApplyUser(hisInstance.getStartUserId());
//            activiti.setApplyTime(hisInstance.getStartTime());
//            activiti.setApplyStatus("申请结束");
//            List<HistoricVariableInstance> varInstanceList = historyService.createHistoricVariableInstanceQuery()
//                    .processInstanceId(hisInstance.getId()).list();
//            ActivitiUtil.setVars(activiti, varInstanceList);
//            activitiList.add(activiti);
//        }
//        return activitiList;
//    }
//
//
//    /**
//     * 我审批的记录列表
//     * @param userName
//     * @return
//     */
//
//    public List<Activiti> myApprovalRecord(String userName) {
//        userName = "huangxu2";
//        List<HistoricProcessInstance> hisProInstance = historyService.createHistoricProcessInstanceQuery()
//                .processDefinitionKey(PROCESS_DEFINE_KEY).involvedUser(userName).finished()
//                .orderByProcessInstanceEndTime().desc().list();
//
//        List<String> auditTaskNameList = new ArrayList<>();
//        auditTaskNameList.add("经理审批");
//        auditTaskNameList.add("总监审批");
//        List<Activiti> activitiList = new ArrayList<>();
//        for (HistoricProcessInstance hisInstance : hisProInstance) {
//            /*List<HistoricTaskInstance> hisTaskInstanceList = historyService.createHistoricTaskInstanceQuery()
//                    .processInstanceId(hisInstance.getId()).processFinished()
//                    .taskAssignee(userName)
//                    .taskNameIn(auditTaskNameList)
//                    .orderByHistoricTaskInstanceEndTime().desc().list();
//            boolean isMyAudit = false;
//            for (HistoricTaskInstance taskInstance : hisTaskInstanceList) {
//                if (taskInstance.getAssignee().equals(userName)) {
//                    isMyAudit = true;
//                }
//            }
//            if (!isMyAudit) {
//                continue;
//            }*/
//            Activiti activiti = new Activiti();
//            activiti.setApplyUser(hisInstance.getStartUserId());
//            activiti.setApplyStatus("申请结束");
//            activiti.setApplyTime(hisInstance.getStartTime());
//            List<HistoricVariableInstance> varInstanceList = historyService.createHistoricVariableInstanceQuery()
//                    .processInstanceId(hisInstance.getId()).list();
//            ActivitiUtil.setVars(activiti, varInstanceList);
//            activitiList.add(activiti);
//        }
//        return activitiList;
//      }
//    }
