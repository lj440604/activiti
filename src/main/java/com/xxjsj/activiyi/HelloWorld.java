package com.xxjsj.activiyi;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class HelloWorld {

	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**�������̶���*/
	@Test
	public void deploymentProcessDefinition(){
		
		RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("diagrams/hellowWorld.bpmn")
										.name("helloWorld����")
										.addClasspathResource("diagrams/hellowWorld.png")
										.deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
		System.out.println(deployment.getDeploymentTime());
		
	}
	
	/**��������ʵ��*/
	@Test
	public void startProcessInstance(){
		String processDefinitionKey = "hellowWorld";
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);

		System.out.println(processInstance.getId());
		System.out.println(processInstance.getProcessDefinitionId());
	}
	
	/**��ѯ��������*/
	@Test
	public void findPersonalTask(){
		String assignee = "����";
		List<org.activiti.engine.task.Task> list = processEngine.getTaskService().createTaskQuery().taskAssignee(assignee).list();
		if(list!=null&&list.size()>0){
			for(Task task:list){
				System.out.println(task.getId());
				System.out.println(task.getName());
				System.out.println(task.getAssignee());
				System.out.println(task.getTaskDefinitionKey());
				System.out.println(task.getProcessInstanceId());
				System.out.println("########################################################");
			}
		}
	}
	
	/**�������*/
	@Test
	public void completeMyPersonalTask(){
		String taskId = "608";
		processEngine.getTaskService().complete(taskId);
		System.out.println("############completeTask############");
	}

}
