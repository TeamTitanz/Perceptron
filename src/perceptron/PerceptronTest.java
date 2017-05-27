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
        float test_outputs[] = new float[2500];

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
        p.Train(inputs, outputs,0.2, 0.05, 200);



        try {
            PrintWriter writer = new PrintWriter("C:\\Users\\User\\Desktop\\Perceptron\\src\\data\\results.txt", "UTF-8");
            int y = 0;
            try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\Perceptron\\src\\data\\test.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] split_text = line.split(",");
                    float x = p.Output(new float[]{
                            Float.parseFloat(split_text[0]),
                            Float.parseFloat(split_text[1]),
                            Float.parseFloat(split_text[2]),
                            Float.parseFloat(split_text[3]),
                            Float.parseFloat(split_text[4])});
                    writer.println(x);
                    test_outputs[y] = x;
                    y++;
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

        for(int c = 0 ; c < 8 ; c++) {
            float val = 0;
            for (int q = 0 ; q < 300; q++){
                val = (float) (val + Math.pow((outputs[300*c + q] - test_outputs[300*c + q]),2));
            }
            float f = (float) Math.sqrt(val);
            System.out.println(f);
            val = 0;
        }

}

}