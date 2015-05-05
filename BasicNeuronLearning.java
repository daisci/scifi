import java.util.ArrayList;
import java.util.Random;


public class BasicNeuronLearning {

	private ArrayList<Layer> layers;
	private ArrayList<float[][]> delta_weight;
	private ArrayList<float[]> grad_expres;

	public BasicNeuronLearning(int layArray[])
	{
		Random rand = new Random();

		layers = new ArrayList<Layer>();
		for (int i = 0; i < layArray.length; ++i)
			layers.add(
					new Layer(
							i == 0 ? 
							layArray[i] : layArray[i - 1], 
							layArray[i], rand)
					);

		delta_weight = new ArrayList<float[][]>();
		for (int i = 0; i < layArray.length; ++i)
			delta_weight.add(new float
						[layers.get(i).size()]
						[layers.get(i).getWeights(0).length]
					 );

		grad_expres = new ArrayList<float[]>();
		for (int i =  0; i < layArray.length; ++i)
			grad_expres.add(new float[layers.get(i).size()]);
	}

	public float[] forward(float[] inputs)
	{

		float outputs[] = new float[inputs.length];

		for( int i = 0; i < layers.size(); ++i ) {  
			outputs = layers.get(i).caculate(inputs);
			inputs = outputs;
		}

		return outputs;
	}

	private float caculateError(float nn_output[], float desired_output[])
	{
		float d[];
		
		if (desired_output.length != nn_output.length)
			d = Layer.add_bias(desired_output);
		else
			d = desired_output;

		float e = 0;
		for (int i = 0; i < nn_output.length; ++i)
			e += (nn_output[i] - d[i]) * (nn_output[i] - d[i]);

		return e;
	}

	public float evaluateQuadraticError(ArrayList<float[]> examples,
								   ArrayList<float[]> results)
	{

		float e = 0;

		for (int i = 0; i < examples.size(); ++i) {
			e += caculateError(forward(examples.get(i)), results.get(i));
		}

		return e;
	}

	private void backPropogate(float[] results) {
		for (int c = layers.size() - 1; c >= 0; --c) {
			for (int i = 0; i < layers.get(c).size(); ++i) {
				if (c == layers.size() - 1) {
					grad_expres.get(c)[i] = 2
							* (layers.get(c).getOutput(i) - results[0])
							* layers.get(c).getActivationDerivative(i);
				} else {
					float sum = 0;
					for (int k = 1; k < layers.get(c + 1).size(); ++k)
						sum += layers.get(c + 1).getWeight(k, i)
								* grad_expres.get(c + 1)[k];
					grad_expres.get(c)[i] = layers.get(c)
							.getActivationDerivative(i) * sum;
				}
			}
		}
	}

	private void resetWeightsDelta()
	{
		for (int c = 0; c < layers.size(); ++c) {
			for (int i = 0; i < layers.get(c).size(); ++i) {
				float weights[] = layers.get(c).getWeights(i);
				for (int j = 0; j < weights.length; ++j)
					delta_weight.get(c)[i][j] = 0;
	        }		
		}
	}

	private void caculateDeltaWeight()
	{
		for (int c = 1; c < layers.size(); ++c) {
			for (int i = 0; i < layers.get(c).size(); ++i) {
				float weights[] = layers.get(c).getWeights(i);
				for (int j = 0; j < weights.length; ++j)
					delta_weight.get(c)[i][j] += grad_expres.get(c)[i] 
					     * layers.get(c-1).getOutput(j);
			}
		}
	}

	private void updateWeights(float learning_rate)
	{
		for (int c = 0; c < layers.size(); ++c) {
			for (int i = 0; i < layers.get(c).size(); ++i) {
				float weights[] = layers.get(c).getWeights(i);
				for (int j = 0; j < weights.length; ++j)
					layers.get(c).setWeight(i, j, layers.get(c).getWeight(i, j) 
							- (learning_rate * delta_weight.get(c)[i][j]));
	        }
		}
	}

	private void learningDate(ArrayList<float[]> examples,
									  ArrayList<float[]> results,
									  float learning_rate)
	{
		resetWeightsDelta();

		for (int l = 0; l < examples.size(); ++l) {
			forward(examples.get(l));
			backPropogate(results.get(l));
			caculateDeltaWeight();
		}

		updateWeights(learning_rate);
	}

	public void training(ArrayList<float[]> examples,
					  ArrayList<float[]> results,
					  float learning_rate)
	{

		float e = Float.POSITIVE_INFINITY;

		while (e > 0.001f) {

			learningDate(examples, results, learning_rate);

			e = evaluateQuadraticError(examples, results);
		}
	}




	public static void main(String[] args) {

		ArrayList<float[]> inputdata = new ArrayList<float[]>();
		ArrayList<float[]> outputLabel = new ArrayList<float[]>();
		for (int i = 0; i < 4; ++i) {
			inputdata.add(new float[2]);
			outputLabel.add(new float[1]);
		}

		inputdata.get(0)[0] = 0;
		inputdata.get(0)[1] = 0;
		outputLabel.get(0)[0] = 0;
		inputdata.get(1)[0] = 1;
		inputdata.get(1)[1] = 1;
		outputLabel.get(1)[0] = 0;
		inputdata.get(2)[0] = 1;
		inputdata.get(2)[1] = 0;
		outputLabel.get(2)[0] = 1;
		inputdata.get(3)[0] = 0;
		inputdata.get(3)[1] = 1;
		outputLabel.get(3)[0] = 1;

		int layerArray[] = { inputdata.get(0).length,
				inputdata.get(0).length * 3, 1 };

		BasicNeuronLearning mlp = new BasicNeuronLearning(layerArray);

		mlp.training(inputdata, outputLabel, 0.3f);
		float error = mlp.evaluateQuadraticError(inputdata, outputLabel);
		System.out.println(1 + " -> error : " + error);


	}
}
