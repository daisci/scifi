import java.util.ArrayList;


public class Layer {

	private int neuron_num, prev_neuron_num;
	private ArrayList<Neuron> neurons;
	private float outputs[];

	public Layer(int prev_n_neurons, int n_neurons, java.util.Random rand)
	{

		neuron_num = n_neurons + 1;
		prev_neuron_num = prev_n_neurons + 1;


		neurons = new ArrayList<Neuron>();
		outputs = new float[neuron_num];

		for (int i = 0; i < neuron_num; ++i)
			neurons.add(new Neuron(prev_neuron_num, rand));
	}


	public static float[] add_bias(float[] in)
	{
		float out[] = new float[in.length + 1];
		for (int i = 0; i < in.length; ++i)
			out[i + 1] = in[i];
		out[0] = 1.0f;
		return out;
	}

	public float[] caculate(float in[])
	{
		float inputs[];

		if (in.length != getWeights(0).length)
			inputs = add_bias(in);
		else
			inputs = in;

		for (int i = 1; i < neuron_num; ++i)
			outputs[i] = neurons.get(i).activate(inputs);

		outputs[0] = 1.0f;

		return outputs;
	}

	public int size() {
		return neuron_num;
	}

	public float getOutput(int i) {
		return outputs[i];
	}

	public float getActivationDerivative(int i) {
		return neurons.get(i).getActivationDerivative();
	}

	public float[] getWeights(int i) {
		return neurons.get(i).getAllNeuWeights();
	}

	public float getWeight(int i, int j) {
		return neurons.get(i).getNeuWeight(j);
	}

	public void setWeight(int i, int j, float v) {
		neurons.get(i).setNeuWeight(j, v);
	}


}
