// Install the Adafruit_VL6180X library
#include "Adafruit_VL6180X.h"

///
// Continuously poll the VL6180X range and send the value
// over the serial port. Each byte read from the serial port
// is one range value. If the value is greater than 239, then
// bits 0-3 are an error value. See Adafruit_VL6180X.h for
// error value definitions.
//
// On arduino nano every, connect Vin to 5v, SCL to A5, and
// SDA to A4

Adafruit_VL6180X vl6180x = Adafruit_VL6180X();
const unsigned long SAMPLE_PERIOD_MS = 20;

void setup() {
  Serial.begin(9600);

  // wait for the serial port to open
  while(!Serial) {
    delay(1);
  }
}

void loop() {
  uint8_t range = vl6180x.readRange();
  uint8_t status = vl6180x.readRangeStatus();

  if(VL6180X_ERROR_NONE == status) {
    Serial.write(range);
  }
  else {
    Serial.write(240 + status);
  }

  delay(SAMPLE_PERIOD_MS);
}
