package com.nc13.springBoard.service;

// Service
// 기존에는 컨트롤러가 DB통신까지 담당했지만
// 이제는 컨트롤러한테는 사용자가 보낸 요청을 처리하고 특정페이지 이동을 해라 라는 책임만을 맡기고
// 실제 데이터베이스와의 통신은 Service 클래스가 담당하게 된다.

import com.nc13.springBoard.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.eclipse.tags.shaded.org.apache.xml.utils.NameSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 서비스의 경우, 스프링 프레임워크가 직접 관리할 수 있도록
// @Service 라는 어노테이션을 붙여주게 된다.
@Service
@RequiredArgsConstructor
public class UserService {
    // 스프링 프레임워크가 관리하고 있는 객체를 불러올 때에는
    // @Autowired 라고 적어주게 된다.
    @Autowired
    private final SqlSession SESSION;

    private final String NAMESPACE = "com.nc13.mappers.UserMapper";

    // 로그인에 사용할 auth 메소드
    public UserDTO auth(UserDTO attempt) {
        // 우리가 이 안에서는 쿼리를 직접 적는 것이 아니라
        // UserMapper.xml 안에 있는 쿼리를 SESSION이 실행시킬 수 있도록
        // 코드를 작성하게 된다.

        // 이 쿼리의 결과는 무조건 1개의 결과만 나와야 하므로
        // SESSION의 selectOne을 실행시키고,
        // 실행시킬 쿼리의 id값과 파라미터를 같이 보내주게 된다.
        return SESSION.selectOne(NAMESPACE+".auth", attempt);
    }

    public boolean validateUsername(UserDTO attempt) {
        return SESSION.selectOne(NAMESPACE + ".selectByUsername", attempt) == null;
    }

    public void register(UserDTO attempt) { // 이미 validateUsername을 통해 중복 확인한 후 register진행하기 때문에 void
        SESSION.insert(NAMESPACE + ".register", attempt);
    }
}
