import { useMainStore } from '@/store';

export function setInterceptors(instance) {
  instance.interceptors.request.use(
    function (config) {
      // accessToken
      const store = useMainStore();
      config.headers.Authorization = 'Bearer ' + store.user.token.accessToken;

      return config;
    },
    function (error) {
      // Do something with request error
      return Promise.reject(error);
    }
  );

  // Add a response interceptor
  instance.interceptors.response.use(
    (response) => {
      return response;
    },
    async (error) => {
      const { config, response } = error;
      const originalRequest = config;

      console.log(response, originalRequest);

      return Promise.reject(error);
    }
  );

  return instance;
}
