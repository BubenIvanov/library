package com.example.library;


import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BooksRepositoryTests {

	/*@Autowired
	BooksRepository repository;

	Book dave, oliver, carter;

	@BeforeEach
	public void setUp() {

		repository.deleteAll();

		dave = repository.save(new Book("Dave", "Matthews"));
		oliver = repository.save(new Book("Oliver August", "Matthews"));
		carter = repository.save(new Book("Carter", "Beauford"));
	}

	@Test
	public void setsIdOnSave() {

		Book dave = repository.save(new Book("Dave", "Matthews"));

		assertThat(dave.id).isNotNull();
	}

	@Test
	public void findsByLastName() {

		List<Book> result = repository.findByLastName("Beauford");

		assertThat(result).hasSize(1).extracting("firstName").contains("Carter");
	}

	@Test
	public void findsByExample() {

		Book probe = new Book(null, "Matthews");

		List<Book> result = repository.findAll(Example.of(probe));

		assertThat(result).hasSize(2).extracting("firstName").contains("Dave", "Oliver August");
	}
*/}
