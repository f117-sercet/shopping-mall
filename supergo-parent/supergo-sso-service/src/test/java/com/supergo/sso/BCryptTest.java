package com.supergo.sso;


import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptTest {

    @Test
    public void testBCrypt() {
        String password = BCrypt.hashpw("123", BCrypt.gensalt());
        System.out.println(password);
    }

    @Test
    public void checkPassword() {
//        String password = "$2a$10$22ZsxeoZMvB9axPvZFVScO6I3ww0jU2m0xFsKUR8Jw1.xNE7VHuPe";
        String password = "$2a$10$m1j4Owfwu1wMcCwLek/KrOXYTlzSdtjn9y10PpKasz9qJ0MISsEGS";

        /**
         * 检查密码
         * 参数1：明文密码
         * 参数2：密文
         */
        Boolean result = BCrypt.checkpw("123", password);
        System.out.println(result);
    }

}
