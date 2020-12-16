package io.kimmking.rpcfx.demo;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.server.RpcfxInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "io.kimmking.rpcfx")
@RestController
public class RpcfxServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpcfxServerApplication.class, args);
	}

	@Autowired
	private RpcfxInvoker invoker;

	@PostMapping("/")
	public RpcfxResponse invoke(@RequestBody RpcfxRequest request) {
		return invoker.invoke(request);
	}

//	@Bean
//	public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver){
//		return new RpcfxInvoker(resolver);
//	}
//
//	@Bean
//	public RpcfxResolver createResolver(){
//		return new DemoResolver();
//	}

	// 能否去掉name
	//
//	@Bean(name = "io.kimmking.rpcfx.demo.api.UserService")
//	public UserService createUserService(){
//		return new UserServiceImpl();
//	}

//	@Bean(name = "io.kimmking.rpcfx.demo.api.OrderService")
//	public OrderService createOrderService(){
//		return new OrderServiceImpl();
//	}

}
