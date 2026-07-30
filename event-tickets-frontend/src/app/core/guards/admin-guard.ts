import { CanActivateFn, Router } from '@angular/router';
import { inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

export const adminGuard: CanActivateFn = () => {

  const router = inject(Router);
  const platformId = inject(PLATFORM_ID);

  if (!isPlatformBrowser(platformId)) {
    return true;
  }

  const role = localStorage.getItem("role");

  if (role === "ADMIN") {

    return true;

  }

  router.navigate(['/login']);

  return false;

};