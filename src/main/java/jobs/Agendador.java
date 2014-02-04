package jobs;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Agendador {
	
	static JobDetail job = JobBuilder.newJob(Tarefa.class).withIdentity("tarefaAloMundo", "group1").build();
	static Trigger trigger = TriggerBuilder.newTrigger()
			.withIdentity("gatilhoAloMundo", "group1")
			.withSchedule(CronScheduleBuilder.cronSchedule("0 0/60 * * * ?"))//Timer de 1hora
			.build();
	
	static Scheduler scheduler;
	public static void inicia() throws Exception {
		System.out.println("Iniciando as Tarefas agendadas!");
		
		scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
	}
	
	public static void para() throws Exception {
		System.out.println("Parando as Tarefas agendadas!");
		scheduler.shutdown();
	}
}
