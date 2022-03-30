package hbue.Utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description TODO
 * @Author 杨开
 * @Date 2022/3/20 17:36
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * 添加静态资源文件，外部可以直接访问地址
     *  资源映射路径
     *  addResourceHandler：访问映射路径
     *  addResourceLocations：资源绝对路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/毕业设计/项目代码/PJFit/src/main/resources/static/upload/");
    }
}