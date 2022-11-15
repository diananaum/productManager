import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.AlreadyExistsException;
import ru.netology.repository.NotFoundException;
import ru.netology.repository.ProductRepository;

public class ProductRepositoryTest {
    ProductRepository repo = new ProductRepository();

    Product book1 = new Book(1, "Hello", 100, "Pushkin");
    Product book2 = new Book(2, "Hello, World", 200, "Alexander");
    Product phone3 = new Smartphone(3, "Three", 30_000, "IThree");
    Product phone4 = new Smartphone(4, "Four", 40_000, "Samfour");


    @Test
    public void shouldAddOne() {
        repo.addProduct(book1);

        Product[] expected = {book1};
        Product[] actual = repo.getProducts();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddTwo() {
        repo.addProduct(phone3);
        repo.addProduct(phone4);

        Product[] expected = {phone3, phone4};
        Product[] actual = repo.getProducts();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddAllAndNew() {
        repo.addProduct(book1);
        repo.addProduct(book2);
        repo.addProduct(phone3);
        repo.addProduct(phone4);

        Product product5 = new Product(5, "Java", 500_000);
        repo.addProduct(product5);

        Product[] expected = {book1, book2, phone3, phone4, product5};
        Product[] actual = repo.getProducts();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        repo.addProduct(book1);
        repo.addProduct(book2);
        repo.addProduct(phone3);
        repo.addProduct(phone4);
        repo.removeById(3);

        Product[] expected = {book1, book2, phone4};
        Product[] actual = repo.getProducts();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSuccessfullyRemoveById () {
        repo.addProduct(book2);
        repo.addProduct(phone3);
        repo.removeById(2);

        Product[] expected = {phone3};
        Product[] actual = repo.getProducts();

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldThrowException() {
        repo.addProduct(book1);
        repo.addProduct(book2);
        repo.addProduct(phone3);
        repo.addProduct(phone4);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(5);
        });
    }

    @Test
    public void shouldSuccessfullyAdd() {
        repo.addProduct(phone4);

        Product[] expected = {phone4};
        Product[] actual = repo.getProducts();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowAlreadyExists() {
        repo.addProduct(book1);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
           repo.addProduct(book1);
        });
    }
}