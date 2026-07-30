import {
  Component,
  OnInit,
  signal
} from '@angular/core';

import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { Category } from '../../../core/models/category';
import { CategoryService } from '../../../core/services/category';
import { CategoryRequest } from '../../../core/models/category-request';

@Component({
  selector: 'app-category',

  imports: [
    ReactiveFormsModule
  ],

  templateUrl: './category.html',

  styleUrl: './category.css'
})
export class CategoryComponent
  implements OnInit {


  categories =
    signal<Category[]>([]);


  showCreateForm =
    false;


  categoryForm: FormGroup;


  constructor(

    private categoryService:
      CategoryService,

    private formBuilder:
      FormBuilder

  ) {

    this.categoryForm =
      this.formBuilder.group({

        name: [

          '',

          [

            Validators.required,

            Validators.minLength(2)

          ]

        ],

        description: [

          ''

        ]

      });

  }


  ngOnInit(): void {

    this.loadCategories();

  }


  loadCategories(): void {

    this.categoryService

      .getCategories()

      .subscribe({

        next: (categories) => {

          this.categories.set(
            categories
          );

        },

        error: (error) => {

          console.error(

            'Error loading categories:',

            error

          );

        }

      });

  }


  openCreateForm(): void {

    this.showCreateForm = true;

    this.categoryForm.reset();

  }


  closeCreateForm(): void {

    this.showCreateForm = false;

    this.categoryForm.reset();

  }


  createCategory(): void {


    if (

      this.categoryForm.invalid

    ) {

      this.categoryForm.markAllAsTouched();

      return;

    }


    const category: CategoryRequest = {

      

      name:
        this.categoryForm
          .get('name')
          ?.value,

      description:
        this.categoryForm
          .get('description')
          ?.value

    };


    this.categoryService

      .saveCategory(category)

      .subscribe({

        next: (savedCategory) => {


          this.categories.update(

            categories => [

              ...categories,

              savedCategory

            ]

          );


          this.closeCreateForm();

        },


        error: (error) => {

          console.error(

            'Error creating category:',

            error

          );

        }

      });

  }


  deleteCategory(id: string): void {

  this.categoryService

    .deleteCategory(id)

    .subscribe({

      next: () => {

        this.categories.update(

          categories =>

            categories.filter(

              category =>

                category.id !== id

            )

        );

      },

      error: (error) => {

        console.error(

          'Error deleting category:',

          error

        );

      }

    });

}

}