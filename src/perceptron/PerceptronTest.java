package perceptron;

import java.io.*;

/**
 *
 * @author Vindula Jayawardana
 */
public class PerceptronTest {

    public static void main(String[] args) {
        Perceptron p = new Perceptron();
        int count = 0;
        float inputs[][] = new float[2500][5];
        float outputs[] = new float[2500];

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\Perceptron\\src\\data\\train.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split_text = line.split(",");
                for(int v = 0; v < 5 ; v++) {
                    inputs[count][v] = Float.parseFloat(split_text[v]);
                }
                outputs[count] = Float.parseFloat(split_text[5]);
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.Train(inputs, outputs,0.2, 0.1, 200);



        try {
            PrintWriter writer = new PrintWriter("C:\\Users\\User\\Desktop\\Perceptron\\src\\data\\results.txt", "UTF-8");

            try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\Perceptron\\src\\data\\test.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] split_text = line.split(",");
                    writer.println(p.Output(new float[]{
                            Float.parseFloat(split_text[0]),
                            Float.parseFloat(split_text[1]),
                            Float.parseFloat(split_text[2]),
                            Float.parseFloat(split_text[3]),
                            Float.parseFloat(split_text[4])}));

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.close();
        }
        catch (IOException e) {
        // do something
    }

}

}