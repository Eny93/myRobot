package test;

import main.Direction;
import main.Position;
import main.RobotService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RobotTest {

    RobotService svc;

    @Before
    public void setUp() {
        svc = new RobotService();
    }

    @Test
    public void testParse() {

        String input = "place 0,1,NORTH\n";

        ArrayList<String> expectedOut = new ArrayList<>(
                Arrays.asList("PLACE", "0", "1", "NORTH"));

        List<String> actual = svc.parse(input);

        Assert.assertEquals(expectedOut, actual);
    }

    @Test
    public void testPlace() {

        String input = "place 0,1,EAST\n";

        String expected = "0,1,EAST";

        List<String> step = svc.parse(input);

        Position p = new Position(0, 0, Direction.NORTH);

        Position actual = svc.execute(step, p);

        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void testLeft() {

        String input = "left\n";

        Position p = new Position(0, 0, Direction.EAST);

        String expected = "0,0,NORTH";

        List<String> step = svc.parse(input);

        Position actual = svc.execute(step, p);

        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void testRight() {

        String input = "right\n";

        Position p = new Position(0, 0, Direction.WEST);

        String expected = "0,0,NORTH";

        List<String> step = svc.parse(input);

        Position actual = svc.execute(step, p);

        Assert.assertEquals(expected, actual.toString());
    }

    @Test
    public void testMove() {

        String input = "move\n";

        Position p = new Position(0, 0, Direction.EAST);

        String expected = "1,0,EAST";

        List<String> step = svc.parse(input);

        Position actual = svc.execute(step, p);

        Assert.assertEquals(expected, actual.toString());
    }


    @Test
    public void testExampleA() {


        Position p = new Position(0, 0, Direction.NORTH);

        String expected = "0,1,NORTH";

        List<String> step1 = svc.parse("PLACE 0,0,NORTH\n");
        List<String> step2 = svc.parse("MOVE\n");
        List<String> step3 = svc.parse("REPORT\n");

        p = svc.execute(step1, p);
        p = svc.execute(step2, p);
        p = svc.execute(step3, p);

        Assert.assertEquals(expected, p.toString());
    }

    @Test
    public void testExampleB() {

        List<String> step1 = svc.parse("PLACE 0,0,NORTH\n");
        List<String> step2 = svc.parse("LEFT\n");
        List<String> step3 = svc.parse("REPORT\n");

        Position p = new Position(0, 0, Direction.NORTH);

        String expected = "0,0,WEST";

        p = svc.execute(step1, p);
        p = svc.execute(step2, p);
        p = svc.execute(step3, p);

        Assert.assertEquals(expected, p.toString());

    }

    @Test
    public void testExampleC() {

        List<String> step1 = svc.parse("PLACE 1,2,EAST\n");
        List<String> step2 = svc.parse("MOVE\n");
        List<String> step3 = svc.parse("MOVE\n");
        List<String> step4 = svc.parse("LEFT\n");
        List<String> step5 = svc.parse("MOVE\n");
        List<String> step6 = svc.parse("REPORT\n");

        Position p = new Position(0, 0, Direction.NORTH);

        String expected = "3,3,NORTH";

        p = svc.execute(step1, p);
        p = svc.execute(step2, p);
        p = svc.execute(step3, p);
        p = svc.execute(step4, p);
        p = svc.execute(step5, p);
        p = svc.execute(step6, p);

        Assert.assertEquals(expected, p.toString());

    }

}
