import tensorflow as tf

print('Built with cuda: %s' % str(tf.test.is_built_with_cuda()))
print('  GPU available: %s' % str(tf.test.is_gpu_available(cuda_only=False, min_cuda_compute_capability=None)))
