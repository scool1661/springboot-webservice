package springboot.domain.posts;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}
		
	@Test
	public void 게시글저장_불러오기() {
		String title = "테스트 제목";
		String content = "테스트 내용";
		
		postsRepository.save(Posts.builder().title(title).content(content).author("박창렴").build());
		List<Posts> postsList = postsRepository.findAll();
		
		Posts posts = postsList.get(0);	
		Assertions.assertThat(posts.getTitle()).isEqualTo(title);
		Assertions.assertThat(posts.getContent()).isEqualTo(content);
	}

}