package ch.idsia.scenarios;

import ch.idsia.ai.agents.Agent;
import ch.idsia.ai.agents.AgentsPool;
import ch.idsia.ai.agents.ai.ForwardJumpingAgent;
import ch.idsia.ai.agents.ai.LargeMLPAgent;
import ch.idsia.ai.agents.ai.SmallMLPAgent;
import ch.idsia.ai.agents.human.HumanKeyboardAgent;
import competition.cig.robinbaumgarten.AStarAgent;
import ch.idsia.ai.tasks.ProgressTask;
import ch.idsia.ai.tasks.Task;
import ch.idsia.tools.CmdLineOptions;
import ch.idsia.tools.EvaluationOptions;

/**
 * Created by IntelliJ IDEA.
 * User: julian
 * Date: May 5, 2009
 * Time: 12:46:43 PM
 */
public class Play {

    public static void main(String[] args) {
        Agent controller = new AStarAgent();
//        Agent controller = new LargeMLPAgent();
        
//        Agent controller = new HumanKeyboardAgent();
        
        
        if (args.length > 0) {
            controller = AgentsPool.load (args[0]);
            AgentsPool.addAgent(controller);
        }
        
//        
//        String[] str = new String[1];
//        str[0] = "-ld 1";
//        EvaluationOptions options = new CmdLineOptions(str);
        
        EvaluationOptions options = new CmdLineOptions(new String[0]);
       
        options.setAgent(controller);
        Task task = new ProgressTask(options);
        options.setMaxFPS(false);
        options.setVisualization(true);
        options.setNumberOfTrials(1);
        options.setMatlabFileName("");
        options.setLevelRandSeed((int) (Math.random () * Integer.MAX_VALUE));
        options.setLevelDifficulty(25);
        options.setLevelType(0);
        task.setOptions(options);

        System.out.println ("Score: " + task.evaluate (controller)[0]);
    }
}
