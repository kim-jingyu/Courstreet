import {useState} from 'react';
import './Signup.style';
import { BackButton, SubmitForm, InputForm, Header, Label, Title, GenderContainer, GenderLabelEx, GenderRadio, Select, SubmitButton, Option, GenderLabelIn, AgeLabel, SignupWrapper, SignupContainer } from './Signup.style';

function Signup() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [name, setName] = useState('');
  const [gender, setGender] = useState('');
  const [age, setAge] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log({ email, password, name, gender, age });
  };

  return (
    <SignupWrapper>
      <SignupContainer>
        <Header>
          <BackButton>&lt;</BackButton>
          <Title>회원가입</Title>
        </Header>
        <SubmitForm onSubmit={handleSubmit}>
          <Label>
            이메일
            <InputForm type='email' value={email} onChange={(e) => setEmail(e.target.value)} placeholder='example@gmail.com' required></InputForm>
          </Label>
          <Label>
            비밀번호
            <InputForm type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="8자리 이상 입력해주세요" required></InputForm>
          </Label>
          <Label>
            이름
            <InputForm type="text" value={name} onChange={(e) => setName(e.target.value)} placeholder="홍길동" required></InputForm>
          </Label>
          <GenderLabelEx>
            성별
            <GenderContainer>
              <GenderLabelIn>
                <GenderRadio type='radio' value='남성' checked={gender === '남성'} onChange={(e) => setGender(e.target.value)}></GenderRadio>
                남성
              </GenderLabelIn>
              <GenderLabelIn>
                <GenderRadio type='radio' value='여성' checked={gender === '여성'} onChange={(e) => setGender(e.target.value)}></GenderRadio>
                여성
              </GenderLabelIn>
            </GenderContainer>
          </GenderLabelEx>
          <AgeLabel>
            나이
            <Select value={age} onChange={(e) => setAge(e.target.value)} required>
              <Option value="" disabled>00</Option>
                {[...Array(150).keys()].map((n) => (
                  <option key={n} value={n + 1}>
                    {n + 1}
                  </option>
                ))}
            </Select>
          </AgeLabel>
          <SubmitButton type='submit'>
            회원가입
          </SubmitButton>
        </SubmitForm>
      </SignupContainer>
    </SignupWrapper>
  );
}

export default Signup;
