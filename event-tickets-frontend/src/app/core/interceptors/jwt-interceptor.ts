import { HttpInterceptorFn } from '@angular/common/http';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {

  if (
    req.url.endsWith('/login') ||
    req.url.endsWith('/register')
  ) {
    return next(req);
  }

  const token = localStorage.getItem('token');

  if (!token) {
    console.log('No JWT token found');
    return next(req);
  }

  const authReq = req.clone({
    setHeaders: {
      Authorization: `Bearer ${token}`
    }
  });

  console.log('✅ JWT attached to:', req.url);

  return next(authReq);
};