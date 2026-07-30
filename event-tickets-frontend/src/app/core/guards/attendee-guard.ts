import { isPlatformBrowser } from '@angular/common';
import { inject, PLATFORM_ID } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const attendeeGuard: CanActivateFn = () => {

  const router = inject(Router);
  const platformId = inject(PLATFORM_ID);

  if (!isPlatformBrowser(platformId)) {
    return true;
  }

  const role = localStorage.getItem("role");

  if (role === "CUSTOMER") {

    return true;

  }

  router.navigate(['/login']);

  return false;

};