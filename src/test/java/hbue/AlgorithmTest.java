package hbue;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hbue.Entity.CommandJob;
import hbue.Entity.Job;
import hbue.Entity.User_job;
import hbue.Service.IUser_jobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.python.core.PyFunction;
import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AlgorithmTest {

    @Autowired
    private IUser_jobService user_jobService;

    @Test
    public void Test1(){
        List<User_job> list = user_jobService.list();
        List<List<Object>> commandList = new ArrayList<>();
        for (User_job user_job:list){
            if (user_job.getUser_job_state() != null && user_job.getCollect() != null && user_job.getUser_job_state() != 0){
                List<Object> command = new ArrayList<>();
                command.add(user_job.getUser_id());
                command.add(user_job.getCollect()+1);
                commandList.add(command);
                command.add(user_job.getJob_id());
            }
        }
        //定义responseBody类为了以json封装返回结果病返回给前端
        String response = "dd" ;
        String str = "";
        //将实体类转换为json类,为了方便转化为string类并以参数传到python文件中
        ObjectMapper om = new ObjectMapper();
        try {
            str = om.writeValueAsString(commandList);//将实体类转换为json类
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //利用Runtime.getRuntime().exec调用python文件，注意eclipse中的文件目录是以项目目录为根目录的，所以在调用的时候可以使用绝对路径，也可以利用相对路径
        try {
            String[] args = new String[] { "python","C:\\Users\\杨开\\PycharmProjects\\pythonProject\\GetCompanyJobs.py", str};
            Process proc = Runtime.getRuntime().exec(args);// 执行py文件//远离同cmd执行一样
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"gb2312"));
            String line = in.readLine();
            while ((line) != null) {
                response = line;
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response);
    }

    @Test
    public void Test2(){
        Integer user_id = 5;
        List<User_job> list = user_jobService.list();
        List<String> commandList = new ArrayList<>();
        for (User_job user_job:list){
            if (user_job.getUser_job_state() != null && user_job.getCollect() != null && user_job.getUser_job_state() != 0){
                String command = "";
                command += user_job.getUser_id().toString();
                command += ",";
                command += Integer.toString((user_job.getCollect()+1));
                command += ",";
                command += user_job.getJob_id().toString();
                commandList.add(command);
            }
        }
        //定义responseBody类为了以json封装返回结果病返回给前端
        String response = "dd" ;
        String str = "";
        //将实体类转换为json类,为了方便转化为string类并以参数传到python文件中
        ObjectMapper om = new ObjectMapper();
        try {
            str = om.writeValueAsString(commandList);//将实体类转换为json类
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        PythonInterpreter pythonInterpreter = new PythonInterpreter();
        pythonInterpreter.execfile("D:\\Pycharm\\Project\\PycharmProjects\\pythonProject\\Getinformation.py");
        PyFunction pyFunction = pythonInterpreter.get("text_create",PyFunction.class);
        PyObject pyObject = pyFunction.__call__(new PyString(user_id.toString()),new PyList(commandList));
        List<Object> jobs = (List) pyObject;
        List<CommandJob> commandJobList = new ArrayList<>();
        for (Object job:jobs){
            CommandJob commandJob = new CommandJob();
            List<Object> commandjob = (List<Object>) job;
            System.out.println(commandjob.get(0));
            System.out.println(commandjob.get(1));
            String key1 = commandjob.get(0).toString();
            String key2 = commandjob.get(1).toString();
            Integer job_id = Integer.parseInt(key1);
            Double similar = Double.parseDouble(key2);
            Job job1 = new Job();
            job1.setJob_id(job_id);
            commandJob.setJob(job1);
            commandJob.setSimilar(similar);
            commandJobList.add(commandJob);
        }
        System.out.println(commandJobList);
    }
}
