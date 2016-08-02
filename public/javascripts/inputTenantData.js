$('.ui.dropdown').dropdown();
$('.ui.radio.checkbox').checkbox();
$('.ui.form')
.form({
  fields: {
    rent: {
      identifier: 'Changetenancy',
      rules: [
        {
          type   : 'empty',
          prompt : 'Please enter your first name the rent at specified location'
        }
      ]
    },
    bedrooms: {
      identifier: 'bedrooms',
      rules: [
        {
          type   : 'empty',
          prompt : 'Please enter number of Bedrooms at specified location'
        }
      ]
    },
    type: {
        identifier: 'type',
        rules: [
          {
            type: 'empty',
            prompt: 'You forgot to give us money , Please select amount to donate'
          }
        ]
      }
  }
})
;