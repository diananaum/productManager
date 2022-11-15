import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;
import ru.netology.repository.ProductRepository;

public class ProductManagerTest {
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Product book1 = new Book(1, "Hello", 100, "Pushkin");
    Product book2 = new Book(2, "Hello, World", 200, "Alexander");
    Product phone3 = new Smartphone(3, "Three", 30_000, "IThree");
    Product phone4 = new Smartphone(4, "Four", 40_000, "Samfour");

    @BeforeEach
    public void setup() {
        manager.add(book1);
        manager.add(book2);
        manager.add(phone3);
        manager.add(phone4);
    }

    @Test
    public void shouldSearchOne() {
        Product[] expected = {phone3};
        Product[] actual = manager.searchBy("Three");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTwo() {
        Product[] expected = {book1, book2};
        Product[] actual = manager.searchBy("Hello");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemove() {
        manager.removeById(1);

        Product[] expected = {book2, phone3, phone4};
        Product[] actual = repo.getProducts();
    }
}