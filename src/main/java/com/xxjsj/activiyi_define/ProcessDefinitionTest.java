package com.xxjsj.activiyi_define;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

public class ProcessDefinitionTest {
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/**�������̶���*/
	@Test
	public void deploymentProcessDefinition(){
		
		RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("diagrams/sonDrinkMilk.bpmn")
										.name("���Ӻ�������")
										.addClasspathResource("diagrams/sonDrinkMilk.png")
										.deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
		System.out.println(deployment.getDeploymentTime());
		
	}
	
	/**�������̶���*/
	@Test
	public void deploymentProcessDefinition_zip(){
		
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("diagrams/Desktop.zip");
		ZipInputStream zipInputStream = new ZipInputStream(in);
		RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deployment = repositoryService.createDeployment()
										.name("���Ӻ�������")
										.addZipInputStream(zipInputStream)
										.deploy();
		System.out.println(deployment.getId());
		System.out.println(deployment.getName());
		System.out.println(deployment.getDeploymentTime());
		
	}
	
}
