package java.org.daisci.nn.mlp;


public class Neuron {

	private float activation;
	private float[] neu_weights;
	static final float lambda = 1.5f;

	public Neuron(int prev_n_neurons, java.util.Random rand)
	{

		neu_weights = new float[prev_n_neurons];

		for (int i = 0; i < prev_n_neurons; ++i)
			neu_weights[i] = rand.nextFloat() - 0.5f;
	}


	public float activate(float inputs[])
	{
		activation = 0.0f;

		for (int i = 0; i < inputs.length; ++i)
			activation += inputs[i] * neu_weights[i];

		return 1.0f / (1.0f + (float) Math.exp((-activation) * lambda));
	}

	public float getActivationDerivative()
	{
		float expmlx = (float) Math.exp(lambda * activation);
		return lambda * expmlx / ((1 + expmlx) * (1 + expmlx));
	}

	public float[] getAllNeuWeights() {
		return neu_weights;
	}

	public float getNeuWeight(int i) {
		return neu_weights[i];
	}

	public void setNeuWeight(int i, float v) {
		neu_weights[i] = v;
	}


}
