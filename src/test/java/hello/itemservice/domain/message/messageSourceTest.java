package hello.itemservice.domain.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class messageSourceTest {

    @Autowired
    MessageSource messageSource;


    @Test
    void helloHessage(){
        String hello = messageSource.getMessage("hello", null, null, null);
        System.out.println(hello);
        Assertions.assertThat(hello).isEqualTo("안녕");
    }

    @Test
    void notFountMessageCode(){
        assertThatThrownBy(() -> messageSource.getMessage("no_co", null, null)).isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFountMessageCode2(){
        String message = messageSource.getMessage("no_co", null, "기본", null);
        Assertions.assertThat(message).isEqualTo("기본");
    }

    @Test
    void notFountMessageCode23(){
        String message = messageSource.getMessage("hello.name", new Object[]{"spring"}, "기본", null);
        Assertions.assertThat(message).isEqualTo("안녕 spring");
    }

    @Test
    void notFountMessageCode234(){
        String message = messageSource.getMessage("hello", new Object[]{"spring"}, Locale.KOREA);
        Assertions.assertThat(message).isEqualTo("안녕");
        String message2 = messageSource.getMessage("hello", new Object[]{"spring"}, Locale.ENGLISH);
        Assertions.assertThat(message2).isEqualTo("hello");
    }

}
