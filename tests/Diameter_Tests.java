import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Diameter_Tests {

    @Test
    void Test1() {
        boolean[][] mat = new boolean[][]{
                {false,true,false,false,false,false},
                {true,false,true,true,false,false},
                {false,true,false,false,false,false},
                {false,true,false,false,true,true},
                {false,false,false,true,false,false},
                {false,false,false,true,false,false}};
        Diameter diam = new Diameter(mat);
        assertEquals(diam.get_diam(), 3);
    }
}
